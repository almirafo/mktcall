package br.com.supportcomm.mktcall.report.classes;

public class RelatorioIntegracaoVO {

	public String nomeCampanha;
	public Long quantidadeLigacoesUnicas;
	public Long comIntegracao;
	public Long semIntegracao;
	public String dataRelatorioInicio;
	public String dataRelatorioFim;
	
	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public Long getQuantidadeLigacoesUnicas() {
		return quantidadeLigacoesUnicas;
	}

	public void setQuantidadeLigacoesUnicas(Long quantidadeLigacoesUnicas) {
		this.quantidadeLigacoesUnicas = quantidadeLigacoesUnicas;
	}

	public Long getComIntegracao() {
		return comIntegracao;
	}

	public void setComIntegracao(Long comIntegracao) {
		this.comIntegracao = comIntegracao;
	}

	public Long getSemIntegracao() {
		return semIntegracao;
	}

	public void setSemIntegracao(Long semIntegracao) {
		this.semIntegracao = semIntegracao;
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
