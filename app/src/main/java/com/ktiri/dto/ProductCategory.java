package com.ktiri.dto;

import java.io.Serializable;

public class ProductCategory implements Serializable {
	private String cate_no; //문자열은 char 쓰지말고 String 으로!!!
	private String cate_name;
	
	public ProductCategory() {
		
	}
	
	public ProductCategory(String cate_no, String cate_name) {
		super();
		this.cate_no = cate_no;
		this.cate_name = cate_name;
	}
	
	public String getCate_no() {
		return cate_no;
	}
	public void setCate_no(String cate_no) {
		this.cate_no = cate_no;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	
}
