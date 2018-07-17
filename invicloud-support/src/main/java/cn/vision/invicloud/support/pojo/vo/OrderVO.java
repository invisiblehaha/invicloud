package cn.vision.invicloud.support.pojo.vo;

import cn.vision.invicloud.support.entity.OrderProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 消费者ID
	 */
	private Integer customerId;
	/**
	 * 支付方式 0=现金支付，1=移动支付，2=刷卡支付
	 */
	private Integer payType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 商品总数
	 */
	private Integer buyAmount;
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount;

	/**
	 * 订单明细表
	 */
	private List<OrderProduct> orderProducts;

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

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
