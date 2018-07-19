package cn.vision.invicloud.support.pojo.vo;


import java.io.Serializable;

public class VIPVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rgtTime;
    private int rgtAmount;

    public String getRgtTime() {
        return rgtTime;
    }

    public int getRgtAmount() {
        return rgtAmount;
    }

    public void setRgtTime(String rgtTime) {
        this.rgtTime = rgtTime;
    }

    public void setRgtAmount(int rgtAmount) {
        this.rgtAmount = rgtAmount;
    }

    @Override
    public String toString() {
        return rgtTime +"\t" + rgtAmount;
    }
}
