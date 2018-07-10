package cn.vision.invicloud.support.common;

import java.io.Serializable;
import java.util.List;

public class BasePageDTO<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 分页信息
	 */
	private PageInfo pageInfo;
	
	/**
	 * 分页实体列表
	 */
	private List<E> list;
	
	public BasePageDTO(PageInfo pageInfo, List<E> list) {
		super();
		this.pageInfo = pageInfo;
		this.list = list;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
}
