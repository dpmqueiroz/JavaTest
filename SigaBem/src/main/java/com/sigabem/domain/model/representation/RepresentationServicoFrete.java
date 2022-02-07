package com.sigabem.domain.model.representation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class RepresentationServicoFrete {

	private String cepOrigem;
	
	private String cepDestino;
	
	private BigDecimal preco;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEntregaPrevisao;
	
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
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public LocalDate getDataEntregaPrevisao() {
		return dataEntregaPrevisao;
	}
	public void setDataEntregaPrevisao(LocalDate dataEntregaPrevisao) {
		this.dataEntregaPrevisao = dataEntregaPrevisao;
	}
	
	
}
