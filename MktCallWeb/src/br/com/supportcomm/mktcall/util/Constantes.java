package br.com.supportcomm.mktcall.util;

/**
 * Arquivos de constantes
 * 
 * @author andre.vilhalba
 * 
 */
public enum Constantes {
	/**
	 * Tipo de Usuário administrador
	 */
	USUARIO_ADMINISTRADOR(1, "ADMIN"),
	/**
	 * Tipo de Usuário administrador Vivo
	 */
	USUARIO_ADMINISTRADOR_VIVO(2, "VIVO"),
	/**
	 * Tipo de Usuário agência
	 */
	USUARIO_AGENCIA(2, "AGENCIA"),
	/**
	 * Tipo de Usuário anunciante
	 */
	USUARIO_ANUNCIANTE(3, "ANUNCIANTE"),
	/**
	 * Status ativo
	 */
	STATUS_ATIVO(1, "ATIVO"),
	/**
	 * Status inativo
	 */
	STATUS_INATIVO(0, "INATIVO"),
	/**
	 * retorna mensagem para ativa
	 */
	STATUS_DESC_ATIVA(1, "ativa"),
	/**
	 * retorna mensagem para inativa
	 */
	STATUS_DESC_INATIVA(0, "inativa"),
	/**
	 * retorna mensagem para inativo
	 */
	STATUS_DESC_INATIVO(0, "inativo"),
	/**
	 * retorna usuarioAutenticado
	 */
	USUARIO_AUTENTICADO(1, "usuarioAutenticado"),

	TIPO_SPOT_FIDELIZADO(5, "FIDELIZADO"), TIPO_SPOT_SMS(4, "SPOT SMS"), TIPO_SPOT_INTERATIVO(2, "SPOT INTERATIVO"), TIPO_SPOT_SMS_ASSINATURA(3, "SPOT SMS/ASSINATURA"), TIPO_SPOT_SIMPLES(1, "SPOT SIMPLES"),
	
	DOMINGO(1,"domingo"),
	SEGUNDA(2,"segunda"),
	TERCA(3,"terca"),
	QUARTA(4,"quarta"),
	QUINTA(5,"quinta"),
	SEXTA(6,"sexto"),
	SABADO(7,"sabado"),
	
	HORA_INICIO(1,"1970-01-01 00:00:00.00"),
	HORA_FIM(2,"1970-01-01 23:59:00.00");
	
	private final int valor;
	private final String descricao;

	private Constantes(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

}
