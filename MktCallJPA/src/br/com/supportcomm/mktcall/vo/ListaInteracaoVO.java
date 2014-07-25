package br.com.supportcomm.mktcall.vo;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ListaInteracaoVO {
	
	@Id
	private String name;
	private Long liguniq;
	private Long semInteracao;
	private Long comInteracao;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLiguniq() {
		return liguniq;
	}
	public void setLiguniq(Long liguniq) {
		this.liguniq = liguniq;
	}
	public Long getSemInteracao() {
		return semInteracao;
	}
	public void setSemInteracao(Long semInteracao) {
		this.semInteracao = semInteracao;
	}
	public Long getComInteracao() {
		return comInteracao;
	}
	public void setComInteracao(Long comInteracao) {
		this.comInteracao = comInteracao;
	}
    
	
}
