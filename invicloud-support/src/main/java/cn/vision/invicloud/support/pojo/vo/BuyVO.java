package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;

public class BuyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String createTime;

    private int buyAmount;

    public String getCreateTime() {
        return createTime;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }

    @Override
    public String toString() {
        return createTime +"\t"+ buyAmount ;
    }
}
