package com.example.util;

/**
 * 分页信息类
 * 
 * @author huxg
 */
public class PageInfo {
	// 当前第几页
	private int currentPageIndex;

	// 向前还是向后, 1- 向前，-1向后
	private int forward = 1;

	// 总记录数
	private long rowCount;

	// 每页显示多少条
	private int pageSize;

	// 实际本页面所显示的行数（即本次查询结果集数目）
	private int showRows;

	// 是否重置当前页数
	private boolean resetPageIndex = false;

	int totalPage;

	boolean isEnd;

	String prevSearchFilter; // 上一页参数
	String nextSearchFilter; // 下一页参数

	public PageInfo() {

	}

	public String getPrevSearchFilter() {
		return prevSearchFilter;
	}

	public void setPrevSearchFilter(String prevSearchFilter) {
		this.prevSearchFilter = prevSearchFilter;
	}

	public String getNextSearchFilter() {
		return nextSearchFilter;
	}

	public void setNextSearchFilter(String nextSearchFilter) {
		this.nextSearchFilter = nextSearchFilter;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	public PageInfo(int pageSize) {
		this(1, pageSize);
	}

	public PageInfo(int currentPageIndex, int pageSize) {
		this.currentPageIndex = currentPageIndex;
		this.pageSize = pageSize;
	}

	public int getCurrentPageIndex() {
		if (getResetPageIndex()) {
			return 1;
		}
		return this.currentPageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setCurrentPageIndex(int i) {
		this.currentPageIndex = i;
	}

	public void setPageSize(int i) {
		this.pageSize = i;
	}

	public long getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(long i) {
		this.rowCount = i;
	}

	public int getShowRows() {
		return this.showRows;
	}

	public void setShowRows(int i) {
		this.showRows = i;
	}

	boolean getResetPageIndex() {
		return this.resetPageIndex;
	}

	public void setResetPageIndex(boolean b) {
		this.resetPageIndex = b;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("pageSize=" + this.pageSize);
		sb.append("currentPageIndex=" + this.currentPageIndex);
		sb.append("rowCount=" + this.rowCount);
		sb.append("showRows=" + this.showRows);
		return sb.toString();
	}

	/**
	 * 得到第一个结果数目
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return new Integer((this.currentPageIndex - 1) * this.pageSize);
	}

	/**
	 * 得到最大结果数目
	 * 
	 * @return
	 */
	public int getMaxResults() {
		// return new Integer((this.currentPageIndex - 1) * this.pageSize
		// + this.pageSize);
		return new Integer(this.pageSize);
	}

	/**
	 * 判断是否已经到达结尾
	 * 
	 * @return
	 */
	public boolean isEnd() {
		// 判定是否已经到达尾页了
		if (rowCount == 0) {
			this.isEnd = true;
		} else {
			// 求余
			long left = rowCount % pageSize;
			if (left == 0) {
				// 能被整除，则总页数应该等于
				long pageCount = rowCount / pageSize;

				// 如果总数大于当前页数，则表示后面还有页数，如果总页数数小于或者等于当前页数，则表示已经没有下一页了
				this.isEnd = !(pageCount > currentPageIndex);
			} else {
				// 余数不为0，则总页数等于除数+1
				long pageCount = (rowCount / pageSize) + 1;
				this.isEnd = !(pageCount > currentPageIndex);
			}
		}
		return this.isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
