package cn.vision.invicloud.web.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveFileUtilsTest {

    @Test
    public void moveFileTest() {
        String file=System.getProperty("user.dir")+"/src/main/java/cn/vision/invicloud/web/analysis/data/1.txt";
        System.out.println(file);
        String newFile=System.getProperty("user.dir")+"/src/main/java/cn/vision/invicloud/web/analysis/";
        MoveFileUtils.moveFile(file, newFile);
    }

    @Test
    public void moveFileTest2() {
        String file="src/main/java/cn/vision/invicloud/web/analysis/data/1.txt";
        System.out.println(file);
        String newFile="src/main/java/cn/vision/invicloud/web/analysis/";
        MoveFileUtils.moveFile(file, newFile);
    }
}