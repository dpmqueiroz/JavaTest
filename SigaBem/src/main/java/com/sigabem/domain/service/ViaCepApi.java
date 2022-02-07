package com.sigabem.domain.service;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCepApi {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("https://viacep.com.br/ws").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
}
