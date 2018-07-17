package cn.vision.invicloud.support.pojo.vo;


import java.io.Serializable;
import java.util.List;

public class CategoryVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    /**
     * 分类ID
     */
	private Long categoryId;
	
    /**
     * 分类名称
     */
	private String name;
	
    /**
     * 页面标题
     */
	private String pageTitle;
	
    /**
     * 页面描述
     */
	private String pageDescription;
	
    /**
     * 页面关键词
     */
	private String pageKeyword;
	
	/**
	 * 商品列表
	 */
	private List<ProductVO> products;

	/**
	 * 子级类目列表
	 */
	private List<CategoryVO> childrenCategorys;
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public String getPageKeyword() {
		return pageKeyword;
	}

	public void setPageKeyword(String pageKeyword) {
		this.pageKeyword = pageKeyword;
	}

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public List<CategoryVO> getChildrenCategorys() {
		return childrenCategorys;
	}

	public void setChildrenCategorys(List<CategoryVO> childrenCategorys) {
		this.childrenCategorys = childrenCategorys;
	}
	
}
