package com.ob.dao;

public interface IQueryMapper {

	String CUSTOMER_INSERT_QRY ="insert into customer values(?,?,?,?,?,SYSDATE,jdbc1_seq1.NEXTVAL)" ;
	String USER_INSERT_QRY ="insert into accountmaster values" ;
	
	String Update_PASSWORD="update user_table set loginPassword=? where accountId=?";
	
	String RETRIVE_PAYEE_ID="select payee_account_id,nickname from payee where account_id=?";
	String PAYEE_INSERT_QRY = "insert into payee values(?,?,?)";
	

}
