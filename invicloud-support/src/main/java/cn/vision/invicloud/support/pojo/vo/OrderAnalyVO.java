package cn.vision.invicloud.support.pojo.vo;

import cn.vision.invicloud.support.entity.OrderProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class OrderAnalyVO implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 消费者ID
     */
    private Integer customerId;
    /**
     *商品ID
     */
    private Integer productId;


    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getProductId() {
        return productId;
    }



    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return customerId +
                "\t" + productId;
    }
}
