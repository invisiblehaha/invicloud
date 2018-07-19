import pandas as pd
import matplotlib.pyplot as plt
from statsmodels.graphics.tsaplots import plot_acf
from statsmodels.tsa.stattools import adfuller as ADF
from statsmodels.graphics.tsaplots import plot_pacf
from statsmodels.stats.diagnostic import acorr_ljungbox
from statsmodels.tsa.arima_model import ARIMA
from numpy import NaN
import datetime
import sys


plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号


if __name__ == '__main__':
    # 参数初始化
    discfile = sys.argv[1]+ '/order_by_pay_amount.txt'
    forecastnum = 7
    # 读取数据，指定日期列为指标，Pandas自动将“日期”列识别为Datetime格式
    data = pd.read_csv(discfile, index_col=0, sep='\t')
    print(data)
    # 时序图
    # data.plot()
    # plt.show()

    # 自相关图
    # plot_acf(data).show()

    # 平稳性检测
    # print(u'原始序列的ADF检验结果为：', ADF(data[u'pay_amount']))
    # 返回值依次为adf、pvalue、usedlag、nobs、critical values、icbest、regresults、resstore

    # 差分后的结果
    D_data = data.diff().dropna()
    D_data.columns = [u'销量差分']
    # D_data.plot()  # 时序图
    # plt.show()
    # plot_acf(D_data).show()  # 自相关图
    # plot_pacf(D_data).show()  # 偏自相关图
    print(u'差分序列的ADF检验结果为：', ADF(D_data[u'销量差分']))  # 平稳性检测

    # 白噪声检验
    print(u'差分序列的白噪声检验结果为：', acorr_ljungbox(D_data, lags=1))  # 返回统计量和p值

    data.iloc[:, 0] = data.iloc[:, 0].astype(float)
    # 定阶
    pmax = int(len(D_data) / 10)  # 一般阶数不超过length/10
    qmax = int(len(D_data) / 10)  # 一般阶数不超过length/10
    bic_matrix = []  # bic矩阵
    for p in range(pmax + 1):
        tmp = []
        for q in range(qmax + 1):
            try:  # 存在部分报错，所以用try来跳过报错。
                tmp.append(ARIMA(data, (p, 1, q)).fit().bic)
            except:
                tmp.append(NaN)
        bic_matrix.append(tmp)

    bic_matrix = pd.DataFrame(bic_matrix)  # 从中可以找出最小值
    # print(bic_matrix)
    p, q = bic_matrix.stack().idxmin()  # 先用stack展平，然后用idxmin找出最小值位置。
    # print(u'BIC最小的p值和q值为：%s、%s' % (p, q))
    model = ARIMA(data, (p, 1, q)).fit()  # 建立ARIMA模型
    model.summary2()  # 给出一份模型报告
    forecast = model.forecast(steps=forecastnum)[0]  # 作为期7天的预测，返回预测结果、标准误差、置信区间。
    # print(forecast)
    # df = pd.DataFrame(data=[], columns=['prediction'])
    size = len(data)
    # data.loc[size] = 1234
    # print(data.iloc[size-1, :].name)
    strtime = data.iloc[size-1, :].name
    last_time = datetime.datetime.strptime(strtime, "%Y-%m-%d %H:%M:%S")
    # data.loc[new_time] = forecast[0]
    # print(data.loc[new_time])
    for i in range(7):
        last_time = last_time + datetime.timedelta(days=1)
        data.loc[last_time] = int(forecast[i])
        print(data.loc[last_time])
    print(data.columns)
    data.rename(columns={data.columns[0]: "pay_amount"}, inplace=True)
    data.index.name = 'DateTime'
    data.to_csv(sys.argv[1]+'/pay_amount_prediction.csv')