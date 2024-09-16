package com.desafio.dio.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.dio.model.Cidade;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CidadeService {

	@GetMapping("/{cep}/json/")
	Cidade consultarCidade(@PathVariable("cep") String cep);
}
