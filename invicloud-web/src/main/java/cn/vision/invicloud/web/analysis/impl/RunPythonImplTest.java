package cn.vision.invicloud.web.analysis.impl;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RunPythonImplTest {
    RunPythonImpl runPython = new RunPythonImpl();
    @org.junit.Test
    public void getCustomerRFMTest() throws IOException {
        runPython.getCustomerRFM();
    }


}