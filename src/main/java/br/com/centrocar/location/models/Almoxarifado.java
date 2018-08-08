package br.com.centrocar.location.models;

public enum Almoxarifado {
	
	CENTROCAR_ESTOQUE("01003"),
	CENTROCAR_VITRINE("01004"),
	CENTROSERVICE_ESTOQUE("03003"),
	CENTROSERVICE_VITRINE("03004"),
	DISPNEU_ESTOQUE("04003"),
	DISPNEU_VITRINE("04004"),
	MEGA_ESTOQUE("05003"),
	MEGA_VITRINE("05004"),
	PNEUSTIL_ESTOQUE("06003"),
	PNEUSTIL_VITRINE("06004");

	private String almoxarifado;

	private Almoxarifado(String almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public String getAlmoxarifado() {
		return this.almoxarifado;
	}
	
	@Override
	public String toString() {
		return this.almoxarifado;
	}
	
}
