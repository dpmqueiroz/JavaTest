package com.sigabem.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServicoFrete {
	
	public ServicoFrete() {

	}
	
	public ServicoFrete(BigDecimal peso, String cepOrigem, String cepDestino, String nomeDestinatario) {
		this.peso = peso;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.nomeDestinatario = nomeDestinatario;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal peso;
	
	@Column(nullable = false)
	private String cepOrigem;
	
	@Column(nullable = false)
	private String cepDestino;
	
	private String nomeDestinatario;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private LocalDate dataConsulta;

	@Column(nullable = false)
	private LocalDate dataEntregaPrevisao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal precoFrete) {
		preco = precoFrete;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public LocalDate getDataEntregaPrevisao() {
		return dataEntregaPrevisao;
	}

	public void setDataEntregaPrevisao(LocalDate dataPrevistaEntrega) {
		this.dataEntregaPrevisao = dataPrevistaEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ServicoFrete other = (ServicoFrete) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
