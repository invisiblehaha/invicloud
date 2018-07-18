package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;

public class PayVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String createTime;

    private int payAmount;

    public String getCreateTime() {
        return createTime;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    @Override
    public String toString() {

        return  createTime +"\t"+ payAmount;
    }
}
