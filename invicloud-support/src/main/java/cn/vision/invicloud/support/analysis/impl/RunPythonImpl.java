package cn.vision.invicloud.support.analysis.impl;

import cn.vision.invicloud.support.analysis.RunPython;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * @author 陶勇聪
 * @date 2018/7/9
 */
public class RunPythonImpl implements RunPython {

    private void runUtil(String pythonPath, String txtPath){
        try{
            System.out.println("start");
            Process pr = Runtime.getRuntime().exec("python "+pythonPath+" "+txtPath);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getRecommedationByData(String pythonPath, String txtPath) throws IOException, InterruptedException {
        runUtil(pythonPath, txtPath);
    }

    @Override
    public void getRecommedationByData2(String pythonPath, String txtPath) throws IOException {
        runUtil(pythonPath, txtPath);
    }

    @Override
    public void getConsumptionLevel(String pythonPath, String txtPath) throws IOException {
        runUtil(pythonPath, txtPath);
    }

    @Override
    public void getBuyAmountPrediction(String pythonPath, String txtPath) throws IOException {
        Runtime.getRuntime().exec("python "+pythonPath+" "+txtPath);
    }

    @Override
    public void getPayAmountPrediction(String pythonPath, String txtPath) throws IOException {
        Runtime.getRuntime().exec("python "+pythonPath+" "+txtPath);
    }

    @Override
    public void getCustomerRFM(String pythonPath, String txtPath) throws IOException {
        runUtil(pythonPath, txtPath);

    }

    @Override
    public void getCategoryRecommendation(String pythonPath, String txtPath) throws IOException {
        runUtil(pythonPath, txtPath);
    }

    @Override
    public void predictVIPIncrease(String pythonPath, String txtPath) throws IOException {
        Runtime.getRuntime().exec("python "+pythonPath+" "+txtPath);
    }

    @Override
    public void test(String pythonPath, String txtPath) throws IOException {
        Runtime.getRuntime().exec("python "+pythonPath+" "+txtPath);
    }
}
