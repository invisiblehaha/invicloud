package cn.vision.invicloud.web.analysis;

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
    void getRecommedationByData() throws IOException;

    /**
     *  得到基于客户的数据分析的推荐结果
     *  保存在 analysis/result/recommendationByProductCF.txt
     */
    void getRecommedationByData2() throws IOException;

    /**
     * 得到用户消费水平
     * @throws IOException
     */
    void getConsumptionLevel() throws IOException;

    /**
     * 得到未来一周内商品销售量
     */
    void getBuyAmountPrediction() throws IOException;

    /**
     * 得到未来一周内商品销售金额
     */
    void getPayAmountPrediction() throws IOException;

    /**
     * 得到客户的等级noble
     */
    void getCustomerRFM() throws IOException;

    /**
     * 得到会员的分类推荐
     * @throws IOException
     */
    void getCategoryRecommendation() throws IOException;

    /**
     * 预测会员注册
     * @throws IOException
     */
    void predictVIPIncrease() throws IOException;
}
