package br.com.centrocar.location.models;

/**
 * Estes enum definem os possíveis tipos para a {@code Locacao}
 * 
 * @author Williams Gomes
 * @see Locacao
 */
public enum TipoLocacao {

	/**
	 * Tipo de locações para itens de dimensão pequena.
	 */
	PEQ("PEQ"),

	/**
	 * Tipo de locações para itens de dimensão média.
	 */
	MED("MED"),

	/**
	 * Tipo de locações para itens de dimensão grande.
	 */
	GRD("GRD"),

	/**
	 * Tipo de locação para itens pesados.
	 */
	PESADO("PESADO"),

	/**
	 * Tipo de locação para latarias.
	 */
	LATARIA("LATARIA"),

	/**
	 * Tipo de locação para radiadores.
	 */
	RADIADOR("RADIADOR"),

	/**
	 * Tipo de locação para escapamentos.
	 */
	ESCAPAMENTO("ESCAPAMENTO"),

	/**
	 * Tipo de locação para para-choques.
	 */
	PARA_CHOQUE("P/ CHOQUE"),

	/**
	 * Tipo de locação para palhetas.
	 */
	PALHETA("PALHETA");

	private String tipo;

	private TipoLocacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return this.tipo;
	}
}
