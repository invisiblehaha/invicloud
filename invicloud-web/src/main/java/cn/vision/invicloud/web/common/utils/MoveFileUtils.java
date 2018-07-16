package cn.vision.invicloud.web.common.utils;

import java.io.File;

/**
 * 移动文件工具类
 * @author 陶勇聪
 * @date 2018/7/16
 */
public class MoveFileUtils {
    public static void moveFile(String oldFilePath, String newFilePath) {
        try {
            File fold = new File(oldFilePath);
            File fnew = new File(newFilePath);
            fold.renameTo(fnew);
            System.out.println("文件移动成功");
        }catch (Exception e){
            System.out.println("文件移动失败");
        }

    }
}
