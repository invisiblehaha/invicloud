package cn.vision.invicloud.web.analysis.impl;

import cn.vision.invicloud.web.analysis.RunPython;

import java.io.IOException;

/**
 * @author 陶勇聪
 * @date 2018/7/9
 */
public class RunPythonImpl implements RunPython {


    @Override
    public void getRecommedationByData() throws IOException {
       Runtime.getRuntime().exec("python src\\main\\java\\cn\\vision\\invicloud\\web\\analysis\\python\\analysis_3.py");
    }

    @Override
    public void getRecommedationByData2() throws IOException {
        Runtime.getRuntime().exec("python src\\main\\java\\cn\\vision\\invicloud\\web\\analysis\\python\\analysis_4.py");

    }

    @Override
    public void getConsumptionLevel() throws IOException {
        Runtime.getRuntime().exec("python src\\main\\java\\cn\\vision\\invicloud\\web\\analysis\\python\\analysis_2.py");
    }

    @Override
    public void getBuyAmountPrediction() throws IOException {
        Runtime.getRuntime().exec("python src\\main\\java\\cn\\vision\\invicloud\\web\\analysis\\python\\predict_buy_amount.py");
    }

    @Override
    public void getPayAmountPrediction() throws IOException {
        Runtime.getRuntime().exec("python src\\main\\java\\cn\\vision\\invicloud\\web\\analysis\\python\\predict_pay_amount.py");
    }

    @Override
    public void getCustomerRFM() throws IOException {
        Runtime.getRuntime().exec("python src\\main\\java\\cn\\vision\\invicloud\\web\\analysis\\python\\analysis_RFM.py");
    }
}
