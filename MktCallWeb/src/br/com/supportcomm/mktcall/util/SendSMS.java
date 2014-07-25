package br.com.supportcomm.mktcall.util;

import br.com.supportcomm.mktcall.util.HpptUtils;

public class SendSMS {
	
	 public  void execute(String number, String mensagem, String proxy, String port) throws Exception{  
		 
		 HpptUtils httpHpptUtils = new HpptUtils(); 
		 
		 String url ="http://limbomc.terra.com:8080/limbomtinc/alrk";
		 String parameter= "";
		 
		 parameter = "?carrier=br.vivo&text=<Mensaje>&to=55<Movil>&from=<Nc>&charging=SC&service=br.creditos";
		 
		 parameter = parameter.replace("${mensagem}", mensagem)
				              .replace("${msisdn}", number);
		 
		 parameter = parameter.replace("<Mensaje>", mensagem)
	              .replace("<Movil>", number)
                  .replace("<Nc>", "3232");   
		 
		 httpHpptUtils.sendGet(url,parameter,proxy,port);
	    }
}
