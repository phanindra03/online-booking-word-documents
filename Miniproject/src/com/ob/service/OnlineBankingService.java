package com.ob.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ob.dao.IOnlineBankingDao;
import com.ob.dao.OnlineBankingDao;
import com.ob.dtobean.CustomerSignUp;
import com.ob.dtobean.NewAccount;
import com.ob.dtobean.Payee;
import com.ob.dtobean.ServiceTracker;
import com.ob.exception.OnlineBankingException;

public class OnlineBankingService implements IOnlineBankingService
{
	
	static IOnlineBankingDao daoobj;

	public OnlineBankingService() {
		daoobj=new OnlineBankingDao();
	}
	
	
	public int validateCustomerLoginDetails(int custuserid, String custpassword) {
		int accountid=daoobj.validateCustomerLoginDetails(custuserid,custpassword);
		return accountid;
	}
	
	@Override
	public int customerAccountBalance(int accountId) {
		
		return daoobj.customerAccountBalance(accountId);
	}
	
	@Override
	public void Request(int acc_id, String description) {
		daoobj.Request(acc_id,description);
		
	}
	
	public List<ServiceTracker> retrieveServiceTrackerByAccountId(int accountId){
		return daoobj.retrieveServiceTrackerByAccountId(accountId);
		
	}
	
	@Override
	public void createNewAccount(NewAccount newcustomer) {
		daoobj.createNewAccount(newcustomer);
		
	}
	@Override
	public void customerSignUp(CustomerSignUp  obs) throws OnlineBankingException {
		daoobj.customerSignUp(obs);
		
	}
	@Override
    public int updateLoginPassword(int userid,String loginPassword) throws OnlineBankingException, SQLException {
		
	return daoobj.updateLoginPassword(userid,loginPassword);
	}


	//validation method for user
	
	@Override
	public boolean validateUserId(int userId) {
		Pattern pattern=Pattern.compile("\\d[1-9][0-9]{5}");
		String userid=Integer.toString(userId);
		Matcher matcher=pattern.matcher(userid);
		if(matcher.equals(userid))
		{
			return true;	
		}
		else{
			System.err.println("Please Enter Valid User Id");
			return false;	
		}
		
	}


	@Override
	public boolean validateLoginPassword(String loginPassword) {
		Pattern pattern=Pattern.compile( "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
		Matcher matcher=pattern.matcher(loginPassword);
		if(matcher.equals(loginPassword))
		{
			return true;	
		}
		else{
			System.err.println("Please Enter Valid Login Password");
			System.out.println("Password should have minimum 1 upper case  1 lower case followed by 1 special character and number");
			return false;	
		}
	}


	@Override
	public boolean validateSecretQuestion(String secretQuestion)
	{
		Pattern pattern=Pattern.compile("");
		Matcher matcher=pattern.matcher(secretQuestion);
		if(matcher.equals(secretQuestion))
		{
			return true;	
		}
		else{
			System.err.println("Please Enter Valid Secret Question");
			return false;	
		}
	}


	@Override
	public boolean validateTransactionPassword(String transactionPassword) {
		Pattern pattern=Pattern.compile( "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
		
		Matcher matcher=pattern.matcher(transactionPassword);
		if(matcher.equals(transactionPassword))
		{
			return true;	
		}
		else{
			System.err.println("Please Enter Valid TransactionPassword ");
			System.out.println("Password should have minimum 1 upper case  1 lower case followed by 1 special character and number");
			return false;	
		}
	}


	@Override
	public int CustomerAccountId() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Payee> retrivePayeeDetails(int accountId) {
		
		return daoobj.retrivePayeeDetails( accountId);
	}


	@Override
	public int storepayeeDetails(int accountId, int payeeAccountId, String nickname) {
		
		return daoobj.storepayeeDetails(accountId,payeeAccountId,nickname);
	}


	

	@Override
	public void updatepayeeaccountbal(int transactionAmount) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updatepayeraccountbal(int transactionAmount) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void fundTransfer(int transaction_id, int accountId, int payeeaccountid, int transactionAmount) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String retrivetransactionpwd(int accountId) {
		String pwd=daoobj.retrivetransactionpwd(int accountId);
		return pwd;
	}


	@Override
	public int checkpayeeAccountId(int payeeAccountId) {
		int result=daoobj.checkpayeeAccountId(payeeAccountId);
		return result;
	}


	@Override  /* case 7*/
	public void updatetransaction(int transaction_id, String transdesc, int transactionAmount, int payeeaccountid,
			String string) {
		daoobj.updatetransaction(transaction_id,transdesc,transactionAmount,payeeaccountid,string);
		
	}
	
	
	
	
	
	
	


}
