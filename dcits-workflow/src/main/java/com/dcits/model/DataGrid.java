package com.dcits.model;

import java.io.Serializable;
import java.util.List;

import org.activiti.engine.repository.Model;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2013 DigitalChina corporation</p>
 *
 * <p>Company: DigitalChina</p>
 *
 * @author guddle
 * @version 1.0
 * @2013-10-13
 */
public class DataGrid<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long total;// 总记录数
	private List<T> rows;// 每行记录
	private List<T> footer;

	public DataGrid(long size, List<T> list) {
		this.total = size;
		this.rows = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}
}
