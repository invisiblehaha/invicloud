package cn.vision.invicloud.web.common.enums;

import humanfaceAPI.Key;

public enum RegisterReturnCode implements IReturnCode{

    REGISTER_FACE_NOT_DETECTED(20000, Key.KEY_FOR_DETECT_FAILED_MESSAGE),
    REGISTER_FACE_NOT_SEARCHED(20004, Key.KEY_FOR_SEARCH_MATCHFAILED_MESSAGE),
    ADDTO_FACESET_FAILED(20001,"Face adding failed: server busy"),
    ADDTO_DATABASE_FAILED(20002,"Data adding failed: database busy"),
    INFO_NOT_COMPLETE(20005,"Register failed: please type in your information in the blank"),
    FACE_REGISTER_TWICE(20003,"Register failed: one same face cann't register twice");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    private RegisterReturnCode(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode()
    {
        return code;
    }
    public String getMessage()
    {
        return message;
    }

}
