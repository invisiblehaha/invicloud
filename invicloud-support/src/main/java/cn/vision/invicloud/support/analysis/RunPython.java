package cn.vision.invicloud.support.analysis;

import java.io.IOException;

/**
 * @author 陶勇聪
 * @date 2018/7/9
 */
public interface RunPython {

    /**
     *  得到基于商品的数据分析的推荐结果
     *  保存在 analysis/result/recommendationByProductCF.txt
     */
    void getRecommedationByData(String pythonPath, String txtPath) throws IOException, InterruptedException;

    /**
     *  得到基于客户的数据分析的推荐结果
     *  保存在 analysis/result/recommendationByProductCF.txt
     */
    void getRecommedationByData2(String pythonPath, String txtPath) throws IOException;

    /**
     * 得到用户消费水平
     * @throws IOException
     */
    void getConsumptionLevel(String pythonPath, String txtPath) throws IOException;

    /**
     * 得到未来一周内商品销售量
     */
    void getBuyAmountPrediction(String pythonPath, String txtPath) throws IOException;

    /**
     * 得到未来一周内商品销售金额
     */
    void getPayAmountPrediction(String pythonPath, String txtPath) throws IOException;

    /**
     * 得到客户的等级noble
     * @throws IOException
     */
    void getCustomerRFM(String pythonPath, String txtPath) throws IOException;

    /**
     * 得到会员的分类推荐
     * @throws IOException
     */
    void getCategoryRecommendation(String pythonPath, String txtPath) throws IOException;

    /**
     * 预测会员注册
     * @throws IOException
     */
    void predictVIPIncrease(String pythonPath, String txtPath) throws IOException;

    /**
     * 测试方法
     * @param pythonPath python 的路径
     * @param txtPath txt 文件的路径
     * @throws IOException 异常
     */
    void test(String pythonPath, String txtPath) throws IOException;
}
