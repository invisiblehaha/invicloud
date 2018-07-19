package cn.vision.invicloud.support.pojo.vo;


import java.io.Serializable;


public class CatAnalyVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer categoryId;
    private Integer buyTotal;

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getBuyTotal() {
        return buyTotal;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setBuyTotal(Integer buyTotal) {
        this.buyTotal = buyTotal;
    }


}
