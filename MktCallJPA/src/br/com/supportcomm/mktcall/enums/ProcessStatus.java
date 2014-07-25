package br.com.supportcomm.mktcall.enums;

public enum ProcessStatus {
	PROCESSANDO("2"),
	PARADADO("0"),
	PREPARADO("3"),
	DISCANDO("4"),
	FINALIZADO("1");
	
	String value;
	
	public String getValue(){
		return this.value;
	}
	ProcessStatus(String value){
		this.value = value;
		
	}

}
