package cn.vision.invicloud.support.pojo.vo;


import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 商品ID
	 */
	private Integer productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 单价
	 */
	private BigDecimal productPrice;
	/**
	 * 商品简介
	 */
	private String productIntroduce;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 展示图片
	 */
	private String picImg;
	/**
	 * 备注
	 */
	private String remarks;
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductIntroduce() {
		return productIntroduce;
	}

	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
