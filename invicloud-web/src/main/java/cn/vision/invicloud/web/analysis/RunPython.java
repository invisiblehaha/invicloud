package cn.vision.invicloud.web.analysis;

import java.io.IOException;

/**
 * @author 陶勇聪
 * @date 2018/7/9
 */
public interface RunPython {
    /**
     *  得到基于商品的数据分析的推荐结果
     *  保存在 analysis/result/recommendationByCustomer.txt
     */
    void getRecommedationByData() throws IOException;

    /**
     *  得到基于客户的数据分析的推荐结果
     *  保存在 analysis/result/recommendationByCustomer.txt
     */
    void getRecommedationByData2() throws IOException;

    void getConsumptionLevel() throws IOException;
}
