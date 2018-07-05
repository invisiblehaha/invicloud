package cn.vision.invicloud.support.common.enums;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Author: Hattori
 * @Date: 2018/7/5 2:09
 * @Description:
 */
public enum CommonReturnCode implements Serializable {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),
    UNKNOWN_ERROR(-1, "未知错误"),
    OK(200, "请求成功"),
    BAD_REQUEST(400, "请求参数出错"),
    UNAUTHORIZED(401, "您未登录或者登录已超时,请先登录!"),
    FORBIDDEN(403, "没有权限"),
    NOT_FOUND(404, "找不到页面");

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    private CommonReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("code",code);
        object.put("message",message);
        System.out.println(object.toString());
        return object.toString();
    }
}
