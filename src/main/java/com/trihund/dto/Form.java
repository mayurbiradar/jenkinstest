package com.trihund.dto;

import java.util.List;

public class Form {
	
	private int fid;
	private int uid;
	
	private List<FormParam> obj;

	

	public List<FormParam> getObj() {
		return obj;
	}

	public void setObj(List<FormParam> obj) {
		this.obj = obj;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}
	
	

}
