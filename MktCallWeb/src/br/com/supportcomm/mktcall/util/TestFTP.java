package br.com.supportcomm.mktcall.util;

public class TestFTP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScpTo sc = new ScpTo();
		
		String user="root";
		String pass="exdbrzl";
		String file="ftpgravado.txt";
		String remoteHost="192.168.3.109";
		String pathLocalFile = "C:/Almir/";
		String pathRemoteFile="/var/supportcomm/MktCall/Audios/Dynamic/";
		sc.execute(file, user,pass, remoteHost, pathLocalFile, pathRemoteFile);
	}

}
