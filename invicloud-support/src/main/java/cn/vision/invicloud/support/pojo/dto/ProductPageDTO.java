package cn.vision.invicloud.support.pojo.dto;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.pojo.vo.ProductVO;

import java.io.Serializable;
import java.util.List;

public class ProductPageDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户列表信息
     */
    private List<ProductVO> productVOs;

    /**
     * 分页信息
     */
    private PageInfo pageInfo;

    public ProductPageDTO(List<ProductVO> productVOs, PageInfo pageInfo) {
        super();
        this.productVOs = productVOs;
        this.pageInfo = pageInfo;
    }

    public List<ProductVO> getProductVOs() {
        return productVOs;
    }

    public void setProductVOs(List<ProductVO> userVOs) {
        this.productVOs = productVOs;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
