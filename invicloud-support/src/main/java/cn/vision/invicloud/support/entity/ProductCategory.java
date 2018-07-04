package cn.vision.invicloud.support.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品分类关联表
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@TableName("crm_product_category")
public class ProductCategory extends Model<ProductCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 分类ID
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建者
     */
    @TableField("create_by")
    private Integer createBy;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
        ", productId=" + productId +
        ", categoryId=" + categoryId +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        "}";
    }
}
