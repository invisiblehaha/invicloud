package cn.vision.invicloud.support.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 规格表

 * </p>
 *
 * @author Hattori
 * @since 2018-07-07
 */
@TableName("crm_specification")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID = 1L;

    /**
     * 规格ID
     */
    @TableId(value = "specification_id", type = IdType.AUTO)
    private Integer specificationId;
    /**
     * 分类ID
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 规格名称
     */
    @TableField("specification_name")
    private String specificationName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.specificationId;
    }

    @Override
    public String toString() {
        return "Specification{" +
        ", specificationId=" + specificationId +
        ", categoryId=" + categoryId +
        ", specificationName=" + specificationName +
        ", createTime=" + createTime +
        "}";
    }
}
