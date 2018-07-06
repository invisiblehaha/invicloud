package cn.vision.invicloud.web.common.enums;

/**
 * @Author: Hattori
 * @Date: 2018/7/5 3:54
 * @Description:
 */
public enum StatusEnum {
    NORMAL(1, "正常"),
    FREEZE(0, "冻结");

    private Integer status;

    private String stateInfo;

    private StatusEnum(Integer status, String stateInfo) {
        this.status = status;
        this.stateInfo = stateInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}
