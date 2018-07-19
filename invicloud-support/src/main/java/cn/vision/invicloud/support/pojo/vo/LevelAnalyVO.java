package cn.vision.invicloud.support.pojo.vo;

import cn.vision.invicloud.support.entity.OrderProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class LevelAnalyVO implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 消费者ID
     */
    private Integer customerId;
    /**
     *购买数量
     */
    private Integer buyAmount;

    /**
     *购买金额
     */
    private BigDecimal payAmount;
    /**
     * 订单数
     */
    private Integer orderNum;
    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public BigDecimal getPayAmont() {
        return payAmount;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public void setPayAmont(BigDecimal payAmont) {
        this.payAmount = payAmont;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return customerId +
                "\t" + buyAmount+"\t"+payAmount+"\t"+orderNum;
    }
}
