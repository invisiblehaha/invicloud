package cn.vision.invicloud.web.common;

import cn.vision.invicloud.web.common.enums.IReturnCode;

import java.io.Serializable;

public class WebResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 返回状态码 */
    private Integer code;

    /** 返回信息 */
    private String message;

    private Object data;

    public WebResult(IReturnCode returnCode){
        super();
        this.code=returnCode.getCode();
        this.message=returnCode.getMessage();
    }

    public WebResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}