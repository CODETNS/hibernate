package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.User;

public class QueryResult {
	
	public QueryResult(List<User> list, int result) {
		super();
		this.list = list;
		this.result = result;
	}
	private List<User> list;
	private int result;
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
}
