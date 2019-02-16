package com.ob.dao;

import java.sql.SQLException;
import java.util.List;

import com.ob.dtobean.CustomerSignUp;
import com.ob.dtobean.NewAccount;
import com.ob.dtobean.Payee;
import com.ob.dtobean.ServiceTracker;
import com.ob.exception.OnlineBankingException;


public interface IOnlineBankingDao {

	abstract int validateCustomerLoginDetails(int custuserid, String custpassword);
	abstract int customerAccountBalance(int accountId);
	abstract void Request(int acc_id, String description);
	abstract List<ServiceTracker> retrieveServiceTrackerByAccountId(int accountId);
	abstract void createNewAccount(NewAccount newcustomer);
	abstract void customerSignUp(CustomerSignUp obs) throws OnlineBankingException;
	abstract int updateLoginPassword(int accountid,String loginPassword) throws OnlineBankingException, SQLException;
	int createAccount(NewAccount newcustomer) throws OnlineBankingException;
	abstract List<Payee> retrivePayeeDetails(int accountId) throws OnlineBankingException;
	abstract int storepayeeDetails(int accountId, int payeeAccountId, String nickname) throws OnlineBankingException;
	
	
	
	abstract void updatepayeeaccountbal(int transactionAmount);    /* case 7  update in user table   */
	abstract void updatepayeraccountbal(int transactionAmount);    /* case 7  update in user table   */
	abstract void fundTransfer(int transaction_id, int accountId, int payeeaccountid, int transactionAmount);  /*  case 7 fund transfetr  */
	abstract String retrivetransactionpwd(int accountId);     /*     case 7    transaction password  from user table */
	abstract List<Payee> retrivePayeeDetails(int accountId);       /*     case 7 from payee table    */
	abstract int storepayeeDetails(int accountId, int payeeAccountId, String nickname);    /*    case 7  to payee table   */
	abstract int checkpayeeAccountId(int payeeAccountId);               /*    case 7   from master table   */
	abstract void updatetransaction(int transaction_id, String transdesc, int transactionAmount, int payeeaccountid,
			String string);                             /*    case 7 from transaction table  */
	
	
	
	

	

	
	
	
	
	
	

	
	
	

	

	

}
