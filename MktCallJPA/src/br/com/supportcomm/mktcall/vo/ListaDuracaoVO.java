package br.com.supportcomm.mktcall.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ListaDuracaoVO {

	 @Id
	 private Long campanha;
	 
     private String nome;
     private Long quantidade;
     private Double media;
     private Long totalsegundos;
     
	public Long getCampanha() {
		return campanha;
	}
	public void setCampanha(Long campanha) {
		this.campanha = campanha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public Long getTotalsegundos() {
		return totalsegundos;
	}
	public void setTotalsegundos(Long totalsegundos) {
		this.totalsegundos = totalsegundos;
	}
          
	
}
