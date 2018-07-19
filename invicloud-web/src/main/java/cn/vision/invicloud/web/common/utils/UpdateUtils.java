package cn.vision.invicloud.web.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.Base64;

public class UpdateUtils {

    private static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 上传字节文件
     * @param uploadByte 字节数组
     * @param key 文件名
     * @return
     */
    public static boolean updatePic(byte[] uploadByte, String key) {
        Configuration cfg = new Configuration(Zone.autoZone());
        UploadManager uploadManager = new UploadManager(cfg);
        String accessKey = "3LmQRdvwD0LWf3YFagACm4OdrCecFhO3o-kqz2Kt";
        String secretKey = "mYSVqbwL-8x1A85iN_sgyosUxSOgCrWoyxz8F_t3";
        String bucket = "tobiastao";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key, 3600, new StringMap().put("insertOnly", 0));
        try {
            Response response = uploadManager.put(uploadByte, key, upToken);
        } catch (QiniuException ex) {
            return false;
        }
        return true;
    }

    /**
     * Base64转byte数组
     * @param b64
     * @return
     */
    public static byte[] Base64ToByteArr(String b64)
    {
        String[] strArr=b64.split(",");
        return decoder.decode(strArr[1]);
    }
}
