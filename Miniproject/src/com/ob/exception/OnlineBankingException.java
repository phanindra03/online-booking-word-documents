package com.ob.exception;

public class OnlineBankingException extends Exception {
	public  OnlineBankingException() {
	}
	public  OnlineBankingException(String msg) {
		System.out.println(msg);
	}

}
