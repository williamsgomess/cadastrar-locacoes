package br.com.centrocar.location.models;

/**
 * Aqui se encontra a estrutura e as regras de negócios de uma locação.
 * 
 * @author Williams Gomes
 * @see TipoLocacao
 */
public class Locacao {

	private Integer id;
	private String area;
	private String rua;
	private String prateleira;
	private String local;
	private Double altura;
	private Double largura;
	private Double profundidade;
	private TipoLocacao tipo;

	/**
	 * Construtor depreciado; apenas para uso da persistência de dados e afins.
	 */
	@Deprecated
	public Locacao() {
	}

	/**
	 * Inicia a instância de uma {@link Locacao}, obrigatoriamente recebendo um
	 * local propriamente dito.
	 * 
	 * @param local
	 */
	public Locacao(String local) throws Exception {
		this.local = local;
	}

	public String getLocal() {
		return local;
	}

	public String getArea() {
		return area;
	}

	public String getRua() {
		return rua;
	}

	public String getPrateleira() {
		return prateleira;
	}

	public Double getAltura() {
		return altura;
	}

	public Double getLargura() {
		return largura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public TipoLocacao getTipo() {
		return tipo;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setPrateleira(String prateleira) {
		this.prateleira = prateleira;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public void setTipo(TipoLocacao tipoLocacaos) {
		this.tipo = tipoLocacaos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", local=" + local + ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		return true;
	}

}
