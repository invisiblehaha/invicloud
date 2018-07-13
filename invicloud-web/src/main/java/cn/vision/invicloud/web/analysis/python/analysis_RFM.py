import numpy as np
import pandas as pd
from sklearn.cluster import KMeans
from sklearn.tree import DecisionTreeClassifier
import os
from sklearn import cross_validation
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from math import sqrt
from sklearn import grid_search
from sklearn.ensemble import RandomForestClassifier
import time
import datetime


if __name__ == '__main__':
    rfm = pd.read_csv('src/main/java/cn/vision/invicloud/web/analysis/data/RFM_data.txt', sep='\t')
    # print(raw_data.head(10))
    temp = rfm['recent'].values
    print(rfm['customer_id'])
    day = []
    # print(temp.head(1))
    now = datetime.datetime.now()
    for i in range(len(temp)):
        mytime = datetime.datetime.strptime(temp[i], "%Y-%m-%d %H:%M:%S")
        day.append((mytime-now).days)
    rfm['recent'] = day
    # print(rfm)
    # 转化为数组
    rfm_new = np.array(rfm[['frequency', 'monetary', u'recent']])

    # 设置随机数
    seed = 9
    # 对数据进行聚类
    clf = KMeans(n_clusters=8, random_state=seed)

    # 拟合模型
    clf = clf.fit(rfm_new)
    # 查看聚类质心点
    # print(clf.cluster_centers_)
    #对原数据表进行类别标记
    rfm['label'] = clf.labels_
    # 查看标记后的数据
    rfm.iloc[:, [0, 4]].to_csv('src/main/java/cn/vision/invicloud/web/analysis/result/RFM_result.txt')
