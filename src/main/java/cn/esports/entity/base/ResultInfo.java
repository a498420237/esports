/*
 * Copyright (c) 2018年4月26日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.entity.base;

import java.util.List;

import cn.esports.entity.MatchInfo.TBean.FilterBean;
import cn.esports.entity.MatchInfo.TBean.ResultBean;

/**
* @Description 
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月26日
* @version 1.0.0
*/
public class ResultInfo<T> {
	   private FilterInfo filter;
       private int level;
       private int limit;
       private int offset;
       private int page;
       private int total;
       private int totalPage;
       private List<T> result;
	public FilterInfo getFilter() {
		return filter;
	}
	public void setFilter(FilterInfo filter) {
		this.filter = filter;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
       
       
}
