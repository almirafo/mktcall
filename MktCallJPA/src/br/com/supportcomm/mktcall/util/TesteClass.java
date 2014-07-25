package br.com.supportcomm.mktcall.util;

import java.sql.Timestamp;

public class TesteClass {

	public String msisdn;
	public Timestamp date;
	public String campanhaName;
	
    public TesteClass(String msisdn,Timestamp date,String campanhaName)  
    {  
       this.msisdn = msisdn;  
       this.date = date;  
       this.campanhaName = campanhaName;  
    }  
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getCampanhaName() {
		return campanhaName;
	}
	public void setCampanhaName(String campanhaName) {
		this.campanhaName = campanhaName;
	}
	
	
	
	
	
}
