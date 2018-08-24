package br.com.centrocar.pesquisa_locacoes.models;

public enum TipoLocacao {

	PEQ("PEQ"),
	MED("MED"),
	GRD("GRD"),
	PESADO("PESADO"),
	LATARIA("LATARIA"),
	RADIADOR("RADIADOR"),
	ESCAPAMENTO("ESCAPAMENTO"),
	PARA_CHOQUE("PARA-CHOQUE"),
	PALHETA("PALHETA");
	
	private String tipo;

	TipoLocacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
