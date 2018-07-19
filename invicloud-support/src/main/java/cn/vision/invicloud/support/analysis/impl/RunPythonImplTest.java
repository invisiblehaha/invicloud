package cn.vision.invicloud.support.analysis.impl;

import org.junit.Test;

import java.io.IOException;

public class RunPythonImplTest {
    RunPythonImpl runPython = new RunPythonImpl();

    @Test
    public void test1Test() throws IOException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\test.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.test(pythonPath,filePath);
    }

    //通过
    @Test
    public void getRecommedationByDataTest() throws IOException, InterruptedException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\analysis_3.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getRecommedationByData(pythonPath,filePath);
    }
    //通过
    @Test
    public void getRecommedationByData2Test() throws IOException, InterruptedException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\analysis_4.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getRecommedationByData2(pythonPath,filePath);
    }
    //通过
    @Test
    public void getConsumptionLevelTest() throws IOException, InterruptedException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\analysis_2.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getConsumptionLevel(pythonPath,filePath);
    }

    @Test
    public void getBuyAmountPredictionTest() throws IOException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\predict_buy_amount.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getBuyAmountPrediction(pythonPath,filePath);
    }

    @Test
    public void getPayAmountPredictionTest() throws IOException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\predict_pay_amount.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getPayAmountPrediction(pythonPath,filePath);
    }
    //通过
    @Test
    public void getCustomerRFMTest() throws IOException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\analysis_RFM.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getCustomerRFM(pythonPath,filePath);
    }
    //通过
    @Test
    public void getCategoryRecommendationTest() throws IOException {
        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\category_recommendation.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.getCategoryRecommendation(pythonPath,filePath);
    }
    //通过
    @Test
    public void predictVIPIncreaseTest() throws IOException {

        String pythonPath = "G:\\GitHub\\invicloud\\invicloud\\out\\artifacts\\invicloud_web_Web_exploded\\WEB-INF\\classes\\python\\predict_VIP_increase.py";
        String filePath ="G:\\Tomcat\\apache-tomcat-9.0.1\\bin";
        runPython.predictVIPIncrease(pythonPath,filePath);
    }

}