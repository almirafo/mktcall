package br.com.supportcomm.mktcall.report.classes;

public class RelatorioEfetividadeVO {

	private String nomeCampanha;
	private int totalExibicoes;
	private int unicos;
	private int ouviuCompleto;
	private int ouviuIncompleto;
	public String dataRelatorioInicio;
	public String dataRelatorioFim;
	public String dataCampanha;

	public String getDataRelatorioInicio() {
		return dataRelatorioInicio;
	}

	public void setDataRelatorioInicio(String dataRelatorioInicio) {
		this.dataRelatorioInicio = dataRelatorioInicio;
	}

	public String getDataRelatorioFim() {
		return dataRelatorioFim;
	}

	public void setDataRelatorioFim(String dataRelatorioFim) {
		this.dataRelatorioFim = dataRelatorioFim;
	}

	public RelatorioEfetividadeVO() {

	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public int getTotalExibicoes() {
		return totalExibicoes;
	}

	public void setTotalExibicoes(int totalExibicoes) {
		this.totalExibicoes = totalExibicoes;
	}

	public int getUnicos() {
		return unicos;
	}

	public void setUnicos(int unicos) {
		this.unicos = unicos;
	}

	public int getOuviuCompleto() {
		return ouviuCompleto;
	}

	public void setOuviuCompleto(int ouviuCompleto) {
		this.ouviuCompleto = ouviuCompleto;
	}

	public int getOuviuIncompleto() {
		return ouviuIncompleto;
	}

	public void setOuviuIncompleto(int ouviuIncompleto) {
		this.ouviuIncompleto = ouviuIncompleto;
	}

	public String getDataCampanha() {
		return dataCampanha;
	}

	public void setDataCampanha(String dataCampanha) {
		this.dataCampanha = dataCampanha;
	}

}
