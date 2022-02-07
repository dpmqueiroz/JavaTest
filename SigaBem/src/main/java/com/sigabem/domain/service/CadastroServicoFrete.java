package com.sigabem.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sigabem.domain.exception.ArgumentoInvalidoException;
import com.sigabem.domain.model.ServicoFrete;
import com.sigabem.domain.model.auxmodel.InfoCep;
import com.sigabem.domain.model.auxmodel.ReqServicoFrete;
import com.sigabem.domain.repository.PrecoFreteRepository;

import reactor.core.publisher.Mono;

@Service
public class CadastroServicoFrete {
	
	@Autowired
	private WebClient viaCepApi;
	
	@Autowired
	private PrecoFreteRepository precoFreteRepository;
	
	public ServicoFrete salvar(ReqServicoFrete servicoFreteRecebido) throws Exception {
		
		validarServicoFrete(servicoFreteRecebido);
		
		Mono<InfoCep> reqCepOrigem = this.viaCepApi.method(HttpMethod.GET).uri("/{cep}/json", servicoFreteRecebido.getCepOrigem()).retrieve().bodyToMono(InfoCep.class);
		Mono<InfoCep> reqCepDestino = this.viaCepApi.method(HttpMethod.GET).uri("/{cep}/json", servicoFreteRecebido.getCepDestino()).retrieve().bodyToMono(InfoCep.class);
		
		InfoCep informacaoCepOrigem = reqCepOrigem.block();
		InfoCep informacaoCepDestino = reqCepDestino.block();
		
		ServicoFrete servicoFrete = new ServicoFrete(servicoFreteRecebido.getPeso(), servicoFreteRecebido.getCepOrigem(), 
				servicoFreteRecebido.getCepDestino(), servicoFreteRecebido.getNomeDestinatario());
		
		LocalDate hoje = LocalDate.now();
		BigDecimal preco = CalculaPreco(servicoFreteRecebido.getPeso());
		
		servicoFrete.setPreco(preco);
		servicoFrete.setDataConsulta(hoje);
		
		if(informacaoCepOrigem.getUf().equals(informacaoCepDestino.getUf())) {
			if(informacaoCepOrigem.getDdd().equals(informacaoCepDestino.getDdd())) {
				servicoFrete.setDataEntregaPrevisao(hoje.plusDays(1));
				servicoFrete.setPreco(preco.multiply(new BigDecimal(0.50).setScale(2)));
				return precoFreteRepository.save(servicoFrete);
			}else {
				servicoFrete.setDataEntregaPrevisao(hoje.plusDays(3));
				servicoFrete.setPreco(preco.multiply(new BigDecimal(0.25).setScale(2)));
				return precoFreteRepository.save(servicoFrete);
			}
		}else {
			servicoFrete.setDataEntregaPrevisao(hoje.plusDays(10));
			servicoFrete.setPreco(preco.setScale(2));
			return precoFreteRepository.save(servicoFrete);
		}
		
	}
	
	public void validarServicoFrete(ReqServicoFrete servicoFreteRecebido) {
		if(!validarServicoFreteRecebido(servicoFreteRecebido)) {
			throw new ArgumentoInvalidoException("O Cep deve conter somente números.");
		}
		
		if(servicoFreteRecebido.getPeso() == BigDecimal.ZERO) {
			throw new ArgumentoInvalidoException("O peso não pode ser igual a 0.");
		}
	}
	
	private boolean validarServicoFreteRecebido(ReqServicoFrete servicoFreteRecebido) {
		
		boolean cepOrigem = servicoFreteRecebido.getCepOrigem().matches("[0-9]{8}");
		boolean cepDestino = servicoFreteRecebido.getCepDestino().matches("[0-9]{8}");
		
		if(cepOrigem && cepDestino) {
			return true;
		}
		
		return false;
	}

	public BigDecimal CalculaPreco(BigDecimal peso) {
		return peso.multiply(new BigDecimal(1));
	}
	
}
