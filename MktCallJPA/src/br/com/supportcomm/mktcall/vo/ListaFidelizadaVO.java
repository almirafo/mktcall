package br.com.supportcomm.mktcall.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ListaFidelizadaVO {

	@Id
	private String campanhaNome;
	private boolean status;
	private Long msisdnContagem;
	private Long idCampanha;

	public ListaFidelizadaVO() {

	}

	public String getCampanhaNome() {
		return campanhaNome;
	}

	public void setCampanhaNome(String campanhaNome) {
		this.campanhaNome = campanhaNome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getMsisdnContagem() {
		return msisdnContagem;
	}

	public void setMsisdnContagem(Long msisdnContagem) {
		this.msisdnContagem = msisdnContagem;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}



}
