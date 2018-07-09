
import pandas


def analy_vip_custom(customer_id, buy_amount, pay_amount, order_amount):

    ava_buy_number = buy_amount/order_amount
    ava_pay_amount = pay_amount/order_amount

    if ava_buy_number > 0:
        return "user_id ="+str(customer_id)+"的会员的每次购物平均消费金额为："+str(int(ava_pay_amount))+", 每次购物所购买平均商品数量为：" + str(int(ava_buy_number))
    else:
        return "user_id ="+str(customer_id)+"的会员暂时未在本店消费"


if __name__ == '__main__':
    result_df = pandas.DataFrame(columns=['customer_id', 'custom'])

    path = None
    separator = None

    path = '../data/2.txt'
    separator = '\t'

    print('开始读取数据')

    # 从源文件读数据
    result = []
    i = 0
    for line in open(path, 'r'):
        data_line = line.split(separator)
        customer_id = int(data_line[0])
        buy_amount = int(data_line[1])
        pay_amount = int(data_line[2])
        order_amount = int(data_line[3])
        temp = analy_vip_custom(customer_id, buy_amount, pay_amount, order_amount)
        print(temp)
        result_df.loc[i] = [customer_id, temp]
        i += 1
    result_df.to_csv('../result/consumptionLevel.txt')
    print(result_df)



