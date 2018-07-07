package cn.vision.invicloud.support.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hattori
 * @since 2018-07-07
 */
@TableName("crm_product_attribute")
public class ProductAttribute extends Model<ProductAttribute> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 规格属性ID
     */
    @TableField("specification_attribute_id")
    private Integer specificationAttributeId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSpecificationAttributeId() {
        return specificationAttributeId;
    }

    public void setSpecificationAttributeId(Integer specificationAttributeId) {
        this.specificationAttributeId = specificationAttributeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ProductAttribute{" +
        ", productId=" + productId +
        ", specificationAttributeId=" + specificationAttributeId +
        ", createTime=" + createTime +
        "}";
    }
}
