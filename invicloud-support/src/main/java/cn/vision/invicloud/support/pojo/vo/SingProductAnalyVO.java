package cn.vision.invicloud.support.pojo.vo;



import java.io.Serializable;
import java.util.Date;

public class SingProductAnalyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date createTime;

    private int buyAmount;

    public SingProductAnalyVO(Date createTime, int buyAmount) {
        this.createTime = createTime;
        this.buyAmount = buyAmount;
    }

    public SingProductAnalyVO() {
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    @Override
    public String toString() {
        return  createTime +"\t"+ buyAmount;
    }
}
