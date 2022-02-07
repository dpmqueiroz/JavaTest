package com.sigabem.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigabem.domain.exception.ArgumentoInvalidoException;
import com.sigabem.domain.model.ServicoFrete;
import com.sigabem.domain.model.auxmodel.ReqServicoFrete;
import com.sigabem.domain.model.representation.RepresentationServicoFrete;
import com.sigabem.domain.repository.PrecoFreteRepository;
import com.sigabem.domain.service.CadastroServicoFrete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/calculo-frete")
@Api(value = "API CalculoFrete")
@CrossOrigin(origins = "*")
public class CalculaFreteController {
	
	@Autowired
	private PrecoFreteRepository precoFreteRepository;
	
	@Autowired CadastroServicoFrete cadastroPrecoFrete;
	
	@GetMapping
	@ApiOperation(value=" Retorna uma lista de Serviços de Frete")
	public ResponseEntity<List<ServicoFrete>> listarTodas() {
		return ResponseEntity.ok(precoFreteRepository.findAll());
	}
	
	@GetMapping("/{idServicoFrete}")
	@ApiOperation(value=" Retorna um único Serviço de Frete")
	public ResponseEntity<ServicoFrete> listarPorId(@PathVariable Long idServicoFrete) {
		Optional<ServicoFrete> precoFrete = precoFreteRepository.findById(idServicoFrete);
		
		if(precoFrete.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(precoFrete.get());
	}
	
	@DeleteMapping("/{idServicoFrete}")
	@ApiOperation(value=" Deleta o Serviço de Frete informado")
	public ResponseEntity<ServicoFrete> deletarPorId(@PathVariable Long idServicoFrete) {
		Optional<ServicoFrete> precoFrete = precoFreteRepository.findById(idServicoFrete);
		
		if(precoFrete.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		precoFreteRepository.delete(precoFrete.get());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping()
	@ApiOperation(value=" Calcula o Frete e retorna o Serviço de Frete")
	public ResponseEntity<?> adicionar(@RequestBody @Valid ReqServicoFrete reqServicoFrete) throws Exception {
		try {
			ServicoFrete servicoFrete = cadastroPrecoFrete.salvar(reqServicoFrete);		
			
			RepresentationServicoFrete representacaoServicoFrete = new RepresentationServicoFrete();
			BeanUtils.copyProperties(servicoFrete, representacaoServicoFrete);
			
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.LOCATION, "localhost:8080/calculo-frete/"+servicoFrete.getId());	
		
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(representacaoServicoFrete);
		} catch (ArgumentoInvalidoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body("erro ao encontrar o(s) CEP(s) que informou.");
		}
			
	}
	
	
}
