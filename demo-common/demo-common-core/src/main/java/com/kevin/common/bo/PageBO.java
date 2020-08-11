package com.kevin.common.bo;

/**
 * 
 * @Description: 分页参数业务对象
 * @author: YULONG.DU
 * @date: 2018年11月5日 上午10:27:31
 */
public class PageBO {
	/** 
	 * 页号
	 */
	private int pageNum;
	/**
	 * 单页大小  如果为-1 则查询全部结果
	 */
	private int size;

	/**
	 * 总大小
	 */
	private int allSize;
	/**
	 * 总页数
	 */
	@SuppressWarnings("unused")
	private int totalPage;
	/**
	 * 排序字符串
	 */
	private String orderBy;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		if(this.size>0 && this.allSize>0) {
			this.getTotalPage();
		}
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    //1+（单页查询数目*（页号-1))
	public int getStartSize() {
		if(this.pageNum>this.getTotalPage()) {
			return 0;
		}
		return 1+(this.size*(this.pageNum-1));
	}

	public int getEndSize() {
		if(this.pageNum>this.getTotalPage()) {
			return 0;
		}
		int endSize = this.size*this.pageNum+1;
		if(endSize>this.allSize) {
			endSize = this.allSize;
		}
		return endSize;
	}

	public int getAllSize() {
		return allSize;
	}

	public void setAllSize(int allSize) {
		this.allSize = allSize;
		if(this.size>0 && this.allSize>0) {
			this.getTotalPage();
		}
	}
    public int getTotalPage() {
    	if(allSize==0 || size==0) {
    		return 0;
    	}
        int totalPage = allSize / size;
        if (allSize % size != 0) {
        	totalPage++;
        }
        return totalPage;
    }
}

	
