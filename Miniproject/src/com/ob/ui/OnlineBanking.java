package com.ob.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ob.dtobean.CustomerSignUp;
import com.ob.dtobean.NewAccount;
import com.ob.dtobean.Payee;
import com.ob.dtobean.ServiceTracker;
import com.ob.exception.OnlineBankingException;
import com.ob.service.IOnlineBankingService;
import com.ob.service.OnlineBankingService;

public class OnlineBanking {
	 static Scanner sc=null;
	 static IOnlineBankingService serobj;
	 static CustomerSignUp csu;
	
	
	public OnlineBanking() {
		serobj=new OnlineBankingService();
	}
	
	/*  main program  */
	public static void main(String[] args) throws OnlineBankingException, SQLException {
		
		sc=new Scanner(System.in);
		/* do while loop for infity  */
		
		do {
		System.out.println("WELCOME TO THE ONLINE BANKING");
		
		System.out.println("PRESS  .....");
		System.out.println("1  ------>  CUSTOMER LOGIN  ");
		System.out.println("2  ------>  ADMINISTRATION LOGIN  ");
		
		int option=sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("WELCOME TO CUSTOMER LOGIN");
			
			customerLoginPage();
			break;

		case 2:
			System.out.println("WELCOME TO ADMINISTRATION LOGIN");
			
			administrationLoginPage();
			
			break;
			
        default:
            System.out.println("EXIT");
			
			System.exit(0);
			break;
		}
		}while(true);
	}
	
	/* customer   sign in and sign up  */
	
	public static void customerLoginPage() throws OnlineBankingException, SQLException {
		
		System.out.println("PRESS 1 FOR SIGN IN AND 2 FOR SIGN UP");
		int custsignin=sc.nextInt();
		switch (custsignin) {
		case 1:
			int accountId=0;
			int count=0;
			do {
		     System.out.println("ENTER YOUR USERID");
		     int custuserid=sc.nextInt();
		
		     System.out.println("ENTER YOUR PASSWORD");
		     String custpassword=sc.nextLine();
		     
		     /* retrive customer account id using username and password */
		/* pass it in customer Login below */
		     accountId=serobj.validateCustomerLoginDetails(custuserid,custpassword);
		     
		     if(accountId==0) {
		    	 System.out.println("INVALID USERID AND PASSWORD");
		    	 System.out.println("PLEASE ENTER VALID USEDID AND PASSWORD");
		    	 count++;
		    	 
		     }else {
		     
		     customerLogin(accountId,custuserid);
		     count=3;
		     }
			}while(accountId==0&&count<3);
			
		  break;
		
		case 2:
		
		     System.out.println("SIGN UP");
		     String custsignup=sc.nextLine();
		     
		     customerSignUp();
		
		  break;
		
		default:
			System.out.println("PLEASE ENTER CORRECT OPTION");
			break;
		}
		
		
	}
	public static void administrationLoginPage() {
		int count=0;
		do {
		System.out.println("ENTER YOUR USERID");
		int adminuserid=sc.nextInt();
		
		
		System.out.println("ENTER YOUR PASSWORD");
		String adminpassword=sc.nextLine();
		
		
		if(adminuserid==1234&&adminpassword=="admin") {
		
		administrationLogin();
		count=3;
		}else {
			System.out.println("ENTER VALID LOGIN DETAILS");
			count++;
		}
		}while(count<3);
	}
	
	public static void customerLogin(int accountId,int custuserid) throws OnlineBankingException, SQLException {
		
		System.out.println("PLEASE SELECT THE SERVICES");
		System.out.println("1  --->  ACCOUNT BALANCE");
		System.out.println("2  --->  MINI/DETAILED STATEMENTS");
		System.out.println("3  --->  ADDRESS CHANGE REQUEST");
		System.out.println("4  --->  MOBILE NO. CHANGE REQUEST");
		System.out.println("5  --->  CHEQUE BOOK REQUEST");
		System.out.println("6  --->  SERVICE REQUEST TRACKER");
		System.out.println("7  --->  FUND TRANSFER");
		System.out.println("8  --->  CHANGE PASSWORD");
		
		int custoption=sc.nextInt();
		
		String description=null;
		switch (custoption) {
		
		case 1:
		    int acc_bal=serobj.customerAccountBalance(accountId);
			System.out.println("YOUR ACCOUNT BALANCE  :  "+acc_bal);
			break;
			
        case 2:
        	/*     mini and detailed transaction    */
			
			break;
			
        case 3:
        	System.out.println("ENTER YOUR NEW ADDRESS");
        	description=sc.next();
        	serobj.Request(accountId,description);
	        System.out.println("REQUEST PROCESSED");
	        break;
	        
        case 4:
        	System.out.println("ENTER YOUR NEW MOBILE NUMBER");
        	description=sc.next();
        	serobj.Request(accountId,description);
	        System.out.println("REQUEST PROCESSED");
	
	        break;
	        
        case 5:
        	int acc_id=serobj.CustomerAccountId();
        	description="NEW CHEQUEBOOK";
        	serobj.Request(accountId,description);
	        System.out.println("REQUEST PROCESSED");
	
	        break;
	        
        case 6:
        	
        	List<ServiceTracker> servicetracker=null;
				servicetracker = serobj.retrieveServiceTrackerByAccountId(accountId);
			for(ServiceTracker st:servicetracker)
			{
			System.out.println(st);	
			}
	 
	        break;
	        
        case 7:
        
        	 List<Payee> payeelist=serobj.retrivePayeeDetails(accountId);         /*       from payee table       */
        	 for(Payee p:payeelist) {
        		 System.out.println(p);
        	 }
        	 System.out.println("ENTER THE PAYEE ACCOUNT ID");
        	 int payeeaccountid=sc.nextInt();
        	 int a=0;
        	 for(Payee l:payeelist) {
        		 if(payeeaccountid==l.getAccountId()) {
        			 a=10;
        			 transaction(accountId,payeeaccountid);
        		 }
        	 }
        	 if(a!=10) {
        		 System.out.println("REGISTER NEW PAYEE ACCOUNT");
        		 System.out.println("ENTER THE PAYEE ACCOUNT ID");
        		 int payeeAccountId=sc.nextInt();
        		 int result=serobj.checkpayeeAccountId( payeeAccountId);    /*       search in account master table      */
        		 
        		 if(result>0) {
        		 System.out.println("ENTER NICKNAME");
        		 String nickname=sc.next();
        		 int status=serobj.storepayeeDetails(accountId,payeeAccountId,nickname);     /*       to payee details        */
        		 
        		 if(status>0) {
        			 System.out.println("data enter successfully");
        		 }else {
        			 System.out.println("data not entered");
        		 }
        		 
        		 
        		 }else {
        			 System.out.println("ENTERED THE WRONG ACCCOUNT ID");
        		 }
        		 
        	 }
        	 
      
       	 
	        break;
	     
        
        case 8:
        	System.out.println("CHANGE PASSWORD FOR USERLOGIN");
        	int count=0;
        	do {
        	System.out.println("ENTER YOUR ACCOUNT ID");
        	int acid=sc.nextInt();
        	
        	if(accountId==acid){
        		String loginPassword;
        		do{
        		System.out.println("ENTER NEW PASSWORD");
        	    loginPassword=sc.next();
        		}while(serobj.validateLoginPassword(loginPassword)==false);
        		count=3;
        		serobj.updateLoginPassword(custuserid,loginPassword);
        	}else {
        		System.err.println("ENTER CORRECT ACCOUNT ID");
        		count++;
        	}
        	}while(count<=3);
        	
        	
        	/*    provide  status       */
	
	        break;
        
		default:
			break;
		}
		
		
	}
	public static void administrationLogin() {
		System.out.println("PLEASE SELECT THE SERVICES");
		System.out.println("1  --->  CREATE NEW ACCOUNT");
		System.out.println("2  --->  VIEW TRANSACTION");
		int adminoption=sc.nextInt();
		switch (adminoption) {
		case 1:
			NewAccount newcustomer=new NewAccount();
			
			System.out.println("PLEASE ENTER YOUR DETAILS");
			System.out.println("ENTER YOUR NAME ");
			String name=sc.nextLine();
			newcustomer.setCustomerName(name);
			
			System.out.println("ENTER ADDRESS DETAILS");
			String address=sc.nextLine();
			newcustomer.setCustomerAddress(address);
			
			System.out.println("ENTER MOBILE NUMBER");
			String mob_num=sc.nextLine();
			newcustomer.setCustomerMobNum(mob_num);
			
			System.out.println("ENTER EMAIL ID");
			String email=sc.nextLine();
			newcustomer.setCustomerEmail(email);
			
			System.out.println("ENTER ACCOUNT TYPE");
			String acc_type=sc.nextLine();
			newcustomer.setAccountType(acc_type);
			
			int id=generateAccountId();
			System.out.println("YOUR ACCOUNT ID GENERATED : "+id);
			newcustomer.setAccountId(id);
			
			System.out.println("OPENING BALANCE");
			String opening_bal=sc.nextLine();
			newcustomer.setAccountBalance(opening_bal);
			
			serobj.createNewAccount(newcustomer);
			
			System.out.println("WELCOME TO ****** BANK");
			
			break;
        
        case 2:
        	System.out.println("ENTER THE ACCOUNT NUMBER");
        	String acc_num=sc.nextLine();
        	System.out.println("  TRANSACTION PERIOD  ");
        	System.out.println(" PRESS 1  --->  FOR 1 DAY ");
        	System.out.println(" PRESS 2  --->  FOR 1 MONTH ");
        	System.out.println(" PRESS 3  --->  FOR 1 YEAR ");
        	System.out.println(" PRESS 4  --->  ALL TRANSACTION");
        	
			
			break;
		default:
			break;
		}
		
	}
	
	
	public static void customerSignUp() throws OnlineBankingException {
		
		 csu=new CustomerSignUp();
		
		System.out.println("ENTER THE ACCOUNT ID");
		int accountId=sc.nextInt();
		csu.setAccountId(accountId);
		
		System.out.println("CREATE USER NAME");
		int userId=sc.nextInt();
		csu.setUserId(userId);
		
		System.out.println("CREATE PASSWORD");
		String loginPassword=sc.next();
		csu.setLoginPassword(loginPassword);
		
		System.out.println("SECRET QUESTION --> WHEN DO YOU GET HIGH ?");
		String secretQuestion=sc.next();
		csu.setSecretQuestion(secretQuestion);
		
		System.out.println("CREATE TRANSACTION PASSWORD");
		String transactionPassword=sc.next();
		csu.setTransactionPassword(transactionPassword);
		
		System.out.println("CREATE LOCK STATUS ****************************");
		String lockStatus=sc.next();
		csu.setLockStatus(lockStatus);
		
		serobj.customerSignUp(csu);
		 
	}
	public static int generateAccountId() {
		int accountId=(int)(Math.random()*1000000000);
		return accountId;
	}
	
	/*       FROM CASE 7   */
	public static void transaction(int accountId,int payeeaccountid){
		String transpwd=null;
    	System.out.println("ENTER THE TRANSACTION PASSWORD");
    	transpwd=sc.next();                 
    	String usertransactionpwd=serobj.retrivetransactionpwd(accountId);
    	if(transpwd==usertransactionpwd&&transpwd!=null) {
    		
  
    	System.out.println("ENTER THE AMOUNT");
    	int transactionAmount=sc.nextInt();           /*      AMOUNT SHOULD BE LESS THAN 10 LAKHS ( take it easy )   */
    	
    	int transaction_id=(int)(Math.random()*1000000);
    	System.out.println("ENTER THE TRANSACTION DESCRIPTION");
    	String transdesc=sc.next();
    	System.out.println("TRANSACTION TYPE");
    	System.out.println(" 1  --->  NEFT");
    	System.out.println(" 2  --->  RTGS");
    	System.out.println(" 3  --->  IMPS");
    	int choice=sc.nextInt();
    	switch (choice) {
    	
		case 1:
		
			
			serobj.updatetransaction(transaction_id,transdesc,transactionAmount,payeeaccountid,"NEFT");    /*  to transaction table */  
			serobj.updatepayeeaccountbal(transactionAmount);                                  /*   to account master    */
			serobj.updatepayeraccountbal(transactionAmount);                                /*    to account master     */
			serobj.fundTransfer(transaction_id,accountId,payeeaccountid,transactionAmount);   /*  to fund transfer table */
			
			System.out.println("NEFT TRANSACTION SUCCESSFUL");
			
			break;
			
			
		case 2:
			
			
			serobj.updatetransaction(transaction_id,transdesc,transactionAmount,payeeaccountid,"RTGS");              
			serobj.updatepayeeaccountbal(transactionAmount);
			serobj.updatepayeraccountbal(transactionAmount);
			serobj.fundTransfer(transaction_id,accountId,payeeaccountid,transactionAmount);
			System.out.println("RTGS TRANSACTION SUCCESSFUL");
			break;
			
		case 3:
			
			
			serobj.updatetransaction(transaction_id,transdesc,transactionAmount,payeeaccountid,"IMPS");          
			serobj.updatepayeeaccountbal(transactionAmount);
			serobj.updatepayeraccountbal(transactionAmount);
			serobj.fundTransfer(transaction_id,accountId,payeeaccountid,transactionAmount);
			System.out.println("IMPS TRANSACTION SUCCESSFUL");
			break;

		default:
			System.out.println("INVALID CHOICE . PLEASE ENTER CORRECT OPTION");
			break;
		}
    	
    	}else {
    		System.out.println("ENTERED WRONG TRANSACTION PASSWORD");
    	}
		
	}

}
