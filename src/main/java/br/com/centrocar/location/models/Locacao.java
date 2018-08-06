package br.com.centrocar.location.models;

import br.com.centrocar.location.exceptions.LocalVazioException;
import br.com.centrocar.location.exceptions.TipoNaoInformadoException;

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
	private String filial;
	
	@Deprecated
	/**
	 * Construtor depreciado; apenas para uso da persistência de dados e afins.
	 */
	public Locacao() { }
	
	/**
	 * Inicia a instância de uma {@link Locacao}, obrigatoriamente recebendo um {@link TipoLocacao}
	 * e um local propriamente dito.
	 * 
	 * @param local
	 * @param tipo
	 * 			Tipo da locação.
	 */
	public Locacao(String local, TipoLocacao tipo) throws Exception {
		
//		if (this.local == null) {
//			throw new LocalVazioException("É precioso informar um local!"); 
//		} else if (this.tipo == null) {
//			throw new TipoNaoInformadoException("Nem tipo de locação informado!");
//		}
		this.local = local;
		this.tipo = tipo;
	}
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
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
