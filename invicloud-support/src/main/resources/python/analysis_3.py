import operator
import random
import math
import time
import pandas
import sys


class Data:
    def __init__(self, dataset='1.txt'):
        """
        无上下文信息的隐性反馈数据集。
        :param dataset: 1.txt
        """

        path = None
        separator = None
        self.customer_id_list = []

        if dataset == '1.txt':
            path = sys.argv[1]+'/1.txt'
            separator = '\t'

        print('开始读取数据')

        # 从源文件读数据
        self.data = []
        customers = []
        for line in open(path, 'r'):
            data_line = line.split(separator)
            customer_id = int(data_line[0])
            product_id = int(data_line[1])

            # 无上下文信息的隐性反馈数据不需要评分和时间截
            #rating = int(data_line[2])
            #timestamp = int(data_line[3])

            customers.append(int(data_line[0]))
            self.data.append([customer_id, product_id])
        # print(self.data[0])
        self.customer_id_list = list(set(customers))
        self.customer_id_list.sort(key=customers.index)
        # print("customer_id:")
        # print(self.customer_id_list)

        def compress(data, col):
            """
            压缩数据data第col列的数据。保证此列数字会从0开始连续出现，中间不会有一个不存在此列的数字。
            :param data: 二维列表数据
            :param col: 要压缩的列
            :return: 此列不同的数字个数（即此列最大数字加1）
            """
            e_rows = dict()  # 键是data数据第col列数据，值是一个存放键出现在的每一个行号的列表
            for i in range(len(data)):
                e = data[i][col]
                if e not in e_rows:
                    e_rows[e] = []
                e_rows[e].append(i)

            for rows, i in zip(e_rows.values(), range(len(e_rows))):
                for row in rows:
                    data[row][col] = i

            return len(e_rows)

        self.num_user = compress(self.data, 0)
        self.num_item = compress(self.data, 1)

        # 训练集和测试集
        self.train, self.test = self.__split_data()
        print('总共有{}条数据，训练集{}，测试集{}，用户{}，物品{}'.format(len(self.data), len(self.train), len(self.test), self.num_user, self.num_item))

    def __split_data(self):
        """
        将数据随机分成8份，1份作为测试集，7份作为训练集
        :return: 训练集和测试集
        """
        test = []
        train = []
        for user, item in self.data:
            if random.randint(1, 8) == 1:
                test.append([user, item])
            else:
                train.append([user, item])
        return train, test


class UserCF:
    def __init__(self, data):
        """
        基于用户的协同过滤算法。
        :param data: 无上下文信息的隐性反馈数据集，包括训练集，测试集等
        """
        self.data = data

        print('基于用户的协同过滤算法')
        print('开始计算用户相似度矩阵')
        self.W = self.__user_similarity()

        self.K = None  # 推荐时选择与用户最相似的用户个数
        self.N = None  # 每个用户最多推荐物品数量
        self.recommendation = None

    def compute_recommendation(self, K=80, N=10):
        """
        开始计算推荐列表
        :param K: 推荐时选择与物品最相似的物品个数
        :param N: 每个用户最多推荐物品数量
        """
        self.K = K
        self.N = N

        print('开始计算推荐列表（K=' + str(self.K) + ', N=' + str(self.N) + '）')
        self.recommendation = self.__get_recommendation()

    def __user_similarity(self):
        """
        计算训练集的用户相似度
        :return: 存放每两个用户之间相似度的二维列表
        """
        train_item_users = [[] for i in range(self.data.num_item)]  # train_item_users[i]是对物品i有过正反馈的所有用户列表
        for user, item in self.data.train:
            train_item_users[item].append(user)

        print('统计每两个用户之间的共同正反馈物品数量和每个用户有过正反馈物品的总量')
        W = [[0 for j in range(self.data.num_user)] for i in
             range(self.data.num_user)]  # W[u][v]是用户u和v的共同有正反馈物品的数量（v>u）
        N = [0 for i in range(self.data.num_user)]  # N[u]是用户u有过正反馈的所有物品的数量
        for users in train_item_users:
            for user in users:
                # 统计N
                N[user] += 1

                # 统计W
                for v in users:
                    if v > user:
                        W[user][v] += 1

        print('计算每两个用户之间的相似度')
        for i in range(self.data.num_user - 1):
            for j in range(i + 1, self.data.num_user):
                if W[i][j] != 0:
                    W[i][j] /= math.sqrt(N[i] * N[j])
                    W[j][i] = W[i][j]
        return W

    def __recommend(self, user, train_user_items):
        """
        对用户user选取最相似的K个用户推荐他们有行为的最多N个物品。
        :param user: 推荐的目标用户
        :param train_user_items: train_user_items[i]是用户i所有有过正反馈的物品集合
        :return: 推荐给用户user的物品列表
        """
        Wu = dict()
        for v in range(self.data.num_user):
            if self.W[user][v] != 0:
                Wu[v] = self.W[user][v]

        # 计算出用户user对每个物品感兴趣程度
        rank = dict()
        for similar_user, similarity_factor in sorted(Wu.items(), key=operator.itemgetter(1), reverse=True)[:self.K]:
            for item in train_user_items[similar_user] - train_user_items[user]:
                rank[item] = rank.setdefault(item, 0) + similarity_factor

        return [r[0] for r in sorted(rank.items(), key=operator.itemgetter(1), reverse=True)[:self.N]]

    def __get_recommendation(self):
        """
        得到所有用户的推荐物品列表。
        :return: 推荐列表，下标i对应给用户i推荐的物品列表
        """
        # 得到训练集中每个用户所有有过正反馈物品集合
        train_user_items = [set() for u in range(self.data.num_user)]
        for user, item in self.data.train:
            train_user_items[user].add(item)

        recommendation = []
        for user in range(self.data.num_user):
            recommendation.append(self.__recommend(user, train_user_items))
        return recommendation


class ItemCF:
    def __init__(self, data):
        """
        基于物品的协同过滤算法。
        :param data: 无上下文信息的隐性反馈数据集，包括训练集，测试集等
        """
        self.data = data

        print('基于物品的协同过滤算法')
        print('开始计算物品相似度矩阵')
        self.W = self.__item_similarity()

        self.K = None  # 推荐时选择与物品最相似的物品个数
        self.N = None  # 每个用户最多推荐物品数量
        self.recommendation = None

    def compute_recommendation(self, K=10, N=10):
        """
        开始计算推荐列表
        :param K: 推荐时选择与物品最相似的物品个数
        :param N: 每个用户最多推荐物品数量
        """
        self.K = K
        self.N = N

        print('开始计算推荐列表（K=' + str(self.K) + ', N=' + str(self.N) + '）')
        self.recommendation = self.__get_recommendation()

    def __item_similarity(self):
        """
        计算训练集的用户相似度
        :return: 存放每两个用户之间相似度的二维列表
        """
        train_user_items = [[] for u in range(self.data.num_user)]  # train_user_items[i]是用户i有过正反馈的所有物品列表
        for user, item in self.data.train:
            train_user_items[user].append(item)

        print('统计每两个物品之间的共同有过正反馈的用户数量和每个物品被有过正反馈的总量')
        W = [[0 for j in range(self.data.num_item)] for i in range(self.data.num_item)]  # W[i][j]是物品i和j的共同被正反馈的数量（j>i）
        N = [0 for i in range(self.data.num_item)]  # N[i]是物品i被有过正反馈的数量
        for items in train_user_items:
            for item in items:
                # 统计N
                N[item] += 1

                # 统计W
                for j in items:
                    if j > item:
                        W[item][j] += 1

        print('计算每两个物品之间的相似度')
        for i in range(self.data.num_item - 1):
            for j in range(i + 1, self.data.num_item):
                if W[i][j] != 0:
                    W[i][j] /= math.sqrt(N[i] * N[j])
                    W[j][i] = W[i][j]
        return W

    def __recommend(self, user_item_set, k_items):
        """
        对每个用户user没有正反馈的物品选取最相似的K个物品计算兴趣，给用户推荐最多N个物品。
        :param user_item_set: 训练集用户user所有有过正反馈的物品集合
        :param k_items: k_items[i]是与物品i最相似的K个物品集合
        :return: 推荐给用户user的物品列表
        """
        rank = dict()
        for i in user_item_set:
            for j in k_items[i]:
                if j not in user_item_set:
                    rank[j] = rank.setdefault(j, 0) + self.W[i][j]
        # for i in set(range(self.data.num_item)) - user_item_set:  # 计算用户user对物品i的兴趣
        #    for j in user_item_set & k_items[i]:
        #        rank[i] = rank.setdefault(i, 0) + self.W[i][j]

        return [items[0] for items in sorted(rank.items(), key=operator.itemgetter(1), reverse=True)[:self.N]]

    def __get_recommendation(self):
        """
        得到所有用户的推荐物品列表。
        :return: 推荐列表，下标i对应给用户i推荐的物品列表
        """
        # 得到训练集中每个用户所有有过正反馈物品集合
        train_user_items = [set() for u in range(self.data.num_user)]
        for user, item in self.data.train:
            train_user_items[user].add(item)

        print('得到每个物品与其最相似的K个物品集合')
        k_items = []
        for i in range(self.data.num_item):
            Wi = dict()  # Wi[j]是物品i和j之间的相似度
            for j in range(self.data.num_item):
                if self.W[i][j] != 0:
                    Wi[j] = self.W[i][j]

            k_items.append(
                set(items[0] for items in sorted(Wi.items(), key=operator.itemgetter(1), reverse=True)[:self.K]))

        print('计算每个用户的推荐列表')
        recommendation = []
        for user_item_set in train_user_items:
            recommendation.append(self.__recommend(user_item_set, k_items))
        return recommendation



if __name__ == '__main__':
    # algorithms = [UserCF, ItemCF]
    precisions = []
    recalls = []
    coverages = []
    popularities = []
    times = []

    data = Data()

    # for algorithm in algorithms:
    startTime = time.time()
    recommend = ItemCF(data)
    recommend.compute_recommendation()
    recommendation = recommend.recommendation
    customer_id_list = data.customer_id_list
    print(customer_id_list)
    print(recommendation)
    result = pandas.DataFrame(columns=['customer_id', 'recommendation'])

    size = len(data.customer_id_list)
    # for i in range(0, size):
    #     result.iloc[i, 0] = customer_id_list[i]
    #     result.iloc[i, 1] = recommendation[i]
    #     print(result.iloc[i])
    result['customer_id'] = customer_id_list
    result['recommendation'] = recommendation

    print(result.iloc[0])

    result.to_csv(sys.argv[1]+'/recommendationByProductCF.txt')
