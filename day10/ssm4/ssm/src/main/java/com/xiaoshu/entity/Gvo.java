package com.xiaoshu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Gvo extends Goods {
	private String tname;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date start;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date end;
	
	

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
