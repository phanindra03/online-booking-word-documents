package com.ob.dtobean;

public class Payee {
	private int accountId;
	private int payeeAccId;
	private String nickName;
	
	public Payee(int accountId, int payeeAccId, String nickName) {
		super();
		this.accountId = accountId;
		this.payeeAccId = payeeAccId;
		this.nickName = nickName;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getPayeeAccId() {
		return payeeAccId;
	}
	public void setPayeeAccId(int payeeAccId) {
		this.payeeAccId = payeeAccId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Payee(int payeeAccId, String nickName) {
		super();
		this.payeeAccId = payeeAccId;
		this.nickName = nickName;
	}
	
	

}
