package com.sigabem.domain.model.auxmodel;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReqServicoFrete {
	
	@NotNull
	private BigDecimal peso;
	
	@NotNull
	@Size(min = 8, max = 8)
	private String cepOrigem;
	
	@NotNull
	@Size(min = 8, max = 8)
	private String cepDestino;
	
	@NotBlank
	private String nomeDestinatario;
	
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
	
}
