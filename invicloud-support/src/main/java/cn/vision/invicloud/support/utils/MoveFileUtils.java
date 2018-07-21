package cn.vision.invicloud.support.utils;

import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * 移动文件工具类
 * @author 陶勇聪
 * @date 2018/7/16
 */
public class MoveFileUtils {
    public static void moveFile(String oldFile, String newFilePath) {
        try {
            File afile = new File(oldFile);
            if (afile.renameTo(new File(newFilePath+afile.getName()))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}