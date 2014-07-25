package br.com.supportcomm.mktcall.util;

import br.com.supportcomm.mktcall.enums.ProcessStatus;

public class TesteEnum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( ProcessStatus.FINALIZADO.getValue());
		System.out.println( ProcessStatus.PARADADO.getValue());
	}

}
