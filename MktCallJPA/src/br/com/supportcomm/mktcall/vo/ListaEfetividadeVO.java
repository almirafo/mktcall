package br.com.supportcomm.mktcall.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ListaEfetividadeVO {

	@Id
	private String nomeCampanha;
	private int totalExibicoes;
	private int usuariosUnicos;
	private int ouviuCompleto;
	private int ouviuIncompleto;
	
	
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
	public int getUsuariosUnicos() {
		return usuariosUnicos;
	}
	public void setUsuariosUnicos(int usuariosUnicos) {
		this.usuariosUnicos = usuariosUnicos;
	}
	
}
