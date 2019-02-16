package com.ob.dtobean;

public class ServiceTracker {
	private int serviceId;
	private String serviceDescription;
	private String accountId;
	private String serviceRequestDate;
	private String serviceStaus;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getServiceRequestDate() {
		return serviceRequestDate;
	}
	public void setServiceRequestDate(String serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}
	public String getServiceStaus() {
		return serviceStaus;
	}
	public void setServiceStaus(String serviceStaus) {
		this.serviceStaus = serviceStaus;
	}
	public ServiceTracker(int serviceId, String serviceDescription, String accountId, String serviceRequestDate,
			String serviceStaus) {
		super();
		this.serviceId = serviceId;
		this.serviceDescription = serviceDescription;
		this.accountId = accountId;
		this.serviceRequestDate = serviceRequestDate;
		this.serviceStaus = serviceStaus;
	}
	public ServiceTracker() {
		
		
	}
	

}
