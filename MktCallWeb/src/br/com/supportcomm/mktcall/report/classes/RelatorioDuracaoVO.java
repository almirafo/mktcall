package br.com.supportcomm.mktcall.report.classes;

public class RelatorioDuracaoVO {

	public String nomeCampanha;
	public Long totalSegundos;
	public Long quantidade;
	public Double media;
	public String dataRelatorioInicio;
	public String dataRelatorioFim;
	
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Double getMedia() {
		return media;
	}
	public void setMedia(Double media) {
		this.media = media;
	}
	public String getNomeCampanha() {
		return nomeCampanha;
	}
	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}
	public Long getTotalSegundos() {
		return totalSegundos;
	}
	public void setTotalSegundos(Long totalSegundos) {
		this.totalSegundos = totalSegundos;
	}
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

}
