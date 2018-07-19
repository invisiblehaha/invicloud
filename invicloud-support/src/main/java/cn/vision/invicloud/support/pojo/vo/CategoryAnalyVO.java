package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;
public class CategoryAnalyVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long customerId;
    /**
     * 分类ID
     */
    private Long categoryId;

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return  customerId +"\t"+ categoryId;
    }
}
