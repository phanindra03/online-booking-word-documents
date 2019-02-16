package com.ob.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ob.dtobean.CustomerSignUp;
import com.ob.dtobean.NewAccount;
import com.ob.dtobean.Payee;
import com.ob.dtobean.ServiceTracker;
import com.ob.exception.OnlineBankingException;
import com.ob.util.DBUtil;
import com.re.dao.IQueryMapper1;
import com.re.dtobean.RechargeDtoBean;
import com.re.exception.RechargeException;


public class OnlineBankingDao implements IOnlineBankingDao{
	Connection conn=null;
	CustomerSignUp customersignup = null;
	NewAccount newAccount =null;
	Payee payee=null;
	
	public void customerSignUp(CustomerSignUp obs) throws OnlineBankingException {
		PropertyConfigurator.configure("resources/log4j.properties");
		Logger log=Logger.getRootLogger();
		conn=DBUtil.DbConnection();
		int status=0;
		int accountNumber=0;
		try {
			PreparedStatement pst=conn.prepareStatement(IQueryMapper.USER_INSERT_QRY);
			pst.setLong(1,customersignup.getAccountId());
			pst.setInt(2,customersignup.getUserId());
			pst.setString(3, customersignup.getLoginPassword());
			pst.setString(4, customersignup.getSecretQuestion());
			pst.setString(5, customersignup.getTransactionPassword());
			pst.setString(6,customersignup.getLockStatus());
		
			status=pst.executeUpdate();
			log.info(status+" data is inserted");
		}catch (SQLException e) {
			log.error("data is not stored:: "+e.getMessage());
			throw new OnlineBankingException("data not stored "+e.getMessage());
		}}
    

	@Override
	public int createAccount(NewAccount newcustomer) throws OnlineBankingException {
		PropertyConfigurator.configure("resources/log4j.properties");
		Logger log=Logger.getRootLogger();
		conn=DBUtil.DbConnection();
		int status=0;
		int accountId=0;
		try {
			PreparedStatement pst=conn.prepareStatement(IQueryMapper.CUSTOMER_INSERT_QRY);
			pst.setString(1, newAccount.getCustomerName());	
			pst.setString(2, newAccount.getCustomerAddress());
			pst.setString(3, newAccount.getCustomerMobNum());
			pst.setString(4, newAccount.getCustomerEmail());
			pst.setString(5, newAccount.getAccountType());
			pst.setString(6, newAccount.getOpeningBalance());
			pst.setString(7, newAccount.getPanCard());
			status=pst.executeUpdate();
			log.info(status+" data is inserted");
			
			
			 ResultSet rs=pst.executeQuery();
			 if(rs.next())
			 {
				accountId=rs.getInt(1);
				log.info(accountId);
			 }
			} catch (SQLException e) {
			log.error("data is not stored:: "+e.getMessage());
			throw new OnlineBankingException("data not stored "+e.getMessage());
		}
		
		return accountId;
	
	}
		
	

	

	@Override
	public int customerAccountBalance(int accountId) {
		/*  retrive customer account balance     */
		return 0;
	}

	
	//Changing login password
	@Override
	public int updateLoginPassword(int userid, String loginPassword) throws OnlineBankingException, SQLException {
		PropertyConfigurator.configure("resources/log4j.properties");
		Logger log=Logger.getRootLogger();
		conn=DBUtil.DbConnection();
		int status=0;
		PreparedStatement pst=conn.prepareStatement(IQueryMapper.Update_PASSWORD);
		pst.setString(1,loginPassword);
		pst.setInt(2, userid);
		status= pst.executeUpdate();
		return status ;
		
		
	}


	@Override
	public int validateCustomerLoginDetails(int custuserid, String custpassword) {
		// TODO Auto-generated method stub
		return 0;
	}


	

	@Override
	public List<ServiceTracker> retrieveServiceTrackerByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createNewAccount(NewAccount newcustomer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void Request(int acc_id, String description) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Payee> retrivePayeeDetails(int accountId) throws OnlineBankingException {
		PropertyConfigurator.configure("resources/log4j.properties");
		Logger log=Logger.getRootLogger();
		conn=DBUtil.DbConnection();
		List<Payee> payeeList=null;
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(IQueryMapper.RETRIVE_PAYEE_ID);
			payeeList=new ArrayList<Payee>();
			Payee payee=null;
			while(rs.next())
			{
				payee=new Payee(rs.getInt(1), rs.getInt(2), rs.getString(3) );
				payeeList.add(payee);
		    } 
		}
		
		catch (SQLException e) 
		{
		
			throw new OnlineBankingException("Data can't be retrieved"+e.getMessage());
		}
		return payeeList;
		
	}


	@Override
	public int storepayeeDetails(int accountId, int payeeAccountId, String nickname) throws OnlineBankingException {
		PropertyConfigurator.configure("resources/log4j.properties");
		Logger log=Logger.getRootLogger();
		conn=DBUtil.DbConnection();
		int status=0;
		
		try {
			PreparedStatement pst=conn.prepareStatement(IQueryMapper.PAYEE_INSERT_QRY);
			pst.setInt(1,accountId);	
			pst.setInt(2,payeeAccountId);
			pst.setString(3, nickname);
		
	/**************************fngknd*/
			
			 
			} catch (SQLException e) {
			log.error("payee data is not stored:: "+e.getMessage());
			throw new OnlineBankingException("data not stored "+e.getMessage());
		}
		
		return 0;
	}




}
