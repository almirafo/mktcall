package br.com.supportcomm.mktcall.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
 




public class HpptUtils {
	       private SendPostRequest data = new SendPostRequest("Mozilla/5.0");
	       
	       /**
	        * Executa metodo get e retorna informações da URL chamada. 
	        * @param HttpServletResponse resp
	        * @param String url
	        * @throws Exception
	        */
		   public boolean sendGet( String url1,String mensagem, String proxysms, String portsms) throws Exception {
			  
			      URL url;
			      HttpURLConnection conn;
			      BufferedReader rd;
			      String line;
			      String result = "";
			      try {
			    	  //SocketAddress enderecoProxy = new InetSocketAddress("proxy.dc1.supp.com.br",  3128);  
					  
			          //Proxy proxy = new Proxy(Proxy.Type.HTTP, enderecoProxy);  
					    
			    	  Proxy proxy = null;
			    	  url = new URL(url1+mensagem); 
			    	 if (!proxysms.equals("")){ 
			    		 SocketAddress enderecoProxy = new InetSocketAddress(proxysms,  Integer.parseInt( portsms));
			    		 proxy = new Proxy(Proxy.Type.HTTP, enderecoProxy); 
				    	 //System.setProperty("http.proxyHost",proxysms) ;
				         //System.setProperty("http.proxyPort", portsms) ;
			    		 conn = (HttpURLConnection) url.openConnection(proxy);
			    	 }else{
			    		 conn = (HttpURLConnection) url.openConnection();
			    	 }
			         
			         
					
			         conn.setRequestMethod("GET");
			         conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
			         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			         while ((line = rd.readLine()) != null) {
			            result += line;
			         }
			         rd.close();
			      } catch (IOException e) {
			         e.printStackTrace();
			      } catch (Exception e) {
			         e.printStackTrace();
			      }
			      return true		  ;
			      }
		  
		  
			/**
			 * Executa metodo get e retorna informações da URL chamada. 
			 * @param resp
			 * @param url
			 * @param urlParameters
			 * @throws Exception
			 */
			public String sendPost(String url,String urlParameters) throws Exception {
				   //SocketAddress enderecoProxy = new InetSocketAddress("proxy.dc1.supp.com.br",  3128);  
					  
		            //Proxy proxy = new Proxy(Proxy.Type.HTTP, enderecoProxy); 
				//url = "http://cube.dc1.supp.com.br/cube-web/api/swapi/subscription-service/subscribe";
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
				//add reuqest header
				con.setRequestMethod("POST");
				con.setRequestProperty("User-Agent", data.getUSER_AGENT());
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		 
				// Send post request
				return executePostRequest(url, con, urlParameters);
		 
			}
			
			private String executePostRequest( String url,
					HttpURLConnection con, String urlParameters) throws IOException {
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();
		 
				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + urlParameters);
				System.out.println("Response Code : " + responseCode);
		 
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
		 
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
		 
				//print result
				System.out.println(response.toString());

				return response.toString();
			}
		
	
}
