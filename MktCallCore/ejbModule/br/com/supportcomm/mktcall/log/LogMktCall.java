package br.com.supportcomm.mktcall.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class LogMktCall {
	static Logger logger = Logger.getLogger("MidWareGuide");
	
	static public void log(Level level, Object text) {
		logger.log(level, text);
	}

	static public void log(Level level, Object text, Throwable t) {
		logger.log(level, text, t);
	}
}
