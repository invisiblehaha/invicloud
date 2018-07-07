package cn.vision.invicloud.support.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author Hattori
 * @since 2018-07-07
 */
@TableName("crm_order_product")
public class OrderProduct extends Model<OrderProduct> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 商品ID
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 购买数量
     */
    @TableField("buy_amount")
    private Integer buyAmount;
    /**
     * 购买金额
     */
    @TableField("pay_amount")
    private BigDecimal payAmount;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        return null;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
        ", orderId=" + orderId +
        ", productId=" + productId +
        ", buyAmount=" + buyAmount +
        ", payAmount=" + payAmount +
        "}";
    }
}
