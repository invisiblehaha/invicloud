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
 * 分类表
 * </p>
 *
 * @author Hattori
 * @since 2018-07-07
 */
@TableName("crm_category")
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;
    /**
     * 父分类ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;
    /**
     * 目录类型 2=二级目录/1=一级目录/0=总目录
     */
    private Integer type;
    /**
     * 状态 1=显示/0=隐藏
     */
    private Integer status;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.categoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
        ", categoryId=" + categoryId +
        ", parentId=" + parentId +
        ", categoryName=" + categoryName +
        ", type=" + type +
        ", status=" + status +
        ", remarks=" + remarks +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
