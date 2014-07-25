package br.com.supportcomm.mktcall.util;

public class SendPostRequest {
	private String USER_AGENT;

	public SendPostRequest(String uSER_AGENT) {
		USER_AGENT = uSER_AGENT;
	}

	public String getUSER_AGENT() {
		return USER_AGENT;
	}

	public void setUSER_AGENT(String uSER_AGENT) {
		USER_AGENT = uSER_AGENT;
	}
}