package cn.vision.invicloud.support.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 规格属性表
 * </p>
 *
 * @author Hattori
 * @since 2018-07-07
 */
@TableName("crm_specification_attribute")
public class SpecificationAttribute extends Model<SpecificationAttribute> {

    private static final long serialVersionUID = 1L;

    /**
     * 规格属性ID
     */
    @TableId(value = "specification_attribute_id", type = IdType.AUTO)
    private Integer specificationAttributeId;
    /**
     * 规格ID
     */
    @TableField("specification_id")
    private Integer specificationId;
    /**
     * 规格属性内容
     */
    @TableField("attribute_name")
    private String attributeName;


    public Integer getSpecificationAttributeId() {
        return specificationAttributeId;
    }

    public void setSpecificationAttributeId(Integer specificationAttributeId) {
        this.specificationAttributeId = specificationAttributeId;
    }

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.specificationAttributeId;
    }

    @Override
    public String toString() {
        return "SpecificationAttribute{" +
        ", specificationAttributeId=" + specificationAttributeId +
        ", specificationId=" + specificationId +
        ", attributeName=" + attributeName +
        "}";
    }
}
