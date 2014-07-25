package br.com.supportcomm.mktcall.util;

/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/**
 * This program will demonstrate the file transfer from local to remote.
 *   $ CLASSPATH=.:../build javac ScpTo.java
 *   $ CLASSPATH=.:../build java ScpTo file1 user@remotehost:file2
 * You will be asked passwd. 
 * If everything works fine, a local file 'file1' will copied to
 * 'file2' on 'remotehost'.
 *
 */
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ScpTo{
	private Session session = null;
    private Channel channel = null;
  public void execute(String file, String user,String pass, String remoteHost, String pathLocalFile, String pathRemoteFile){

	  try {
		JSch ssh = new JSch();
		  
		  session = ssh.getSession(user, remoteHost, 22);
		  session.setConfig("StrictHostKeyChecking", "no");
		  session.setPassword(pass);
		  session.connect();
		  
		  channel = session.openChannel("sftp");
		  channel.connect();
		  
		  ChannelSftp sftp = (ChannelSftp) channel;
		  sftp.put(pathLocalFile.concat(file) , pathRemoteFile.concat(file));
		  
	} catch (JSchException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SftpException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
	}
	  
  
  }
 }



