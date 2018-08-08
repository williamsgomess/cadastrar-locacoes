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
	PEQ(1),

	/**
	 * Tipo de locações para itens de dimensão média.
	 */
	MED(2),

	/**
	 * Tipo de locações para itens de dimensão grande.
	 */
	GRD(3),

	/**
	 * Tipo de locação para itens pesados.
	 */
	PESADO(4),

	/**
	 * Tipo de locação para latarias.
	 */
	LATARIA(5),

	/**
	 * Tipo de locação para radiadores.
	 */
	RADIADOR(6),

	/**
	 * Tipo de locação para escapamentos.
	 */
	ESCAPAMENTO(7),

	/**
	 * Tipo de locação para para-choques.
	 */
	PARA_CHOQUE(8),

	/**
	 * Tipo de locação para palhetas.
	 */
	PALHETA(9);

	private Integer tipo;

	private TipoLocacao(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return this.tipo;
	}
	
//	@Override
//	public Integer toString() {
//		return this.tipo;
//	}
}
