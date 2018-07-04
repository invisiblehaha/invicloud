package cn.vision.invicloud.support.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@TableName("crm_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;
    /**
     * 消费者ID
     */
    @TableField("customer_id")
    private Integer customerId;
    /**
     * 支付方式 0=现金支付，1=移动支付，2=刷卡支付
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 商品总数
     */
    @TableField("buy_amount")
    private Integer buyAmount;
    /**
     * 支付金额
     */
    @TableField("pay_amount")
    private BigDecimal payAmount;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
        ", orderId=" + orderId +
        ", customerId=" + customerId +
        ", payType=" + payType +
        ", createTime=" + createTime +
        ", buyAmount=" + buyAmount +
        ", payAmount=" + payAmount +
        "}";
    }
}
