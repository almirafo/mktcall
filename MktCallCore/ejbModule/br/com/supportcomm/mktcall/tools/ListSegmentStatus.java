package br.com.supportcomm.mktcall.tools;
/**
 * Estrutura de retorno de processamento do arquivo de Segmentos
 * @author almir.oliveira
 *
 */
public class ListSegmentStatus {
	private boolean processado;
	private long numRowsNotProcess;
	private long numRowsProcess;
	public boolean isProcessado() {
		return processado;
	}
	public void setProcessado(boolean processado) {
		this.processado = processado;
	}
	public long getNumRowsNotProcess() {
		return numRowsNotProcess;
	}
	public void setNumRowsNotProcess(long numRowsNotProcess) {
		this.numRowsNotProcess = numRowsNotProcess;
	}
	public long getNumRowsProcess() {
		return numRowsProcess;
	}
	public void setNumRowsProcess(long numRowsProcess) {
		this.numRowsProcess = numRowsProcess;
	}
	

}
