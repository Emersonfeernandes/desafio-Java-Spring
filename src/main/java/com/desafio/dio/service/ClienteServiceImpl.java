package com.desafio.dio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.dio.model.Cidade;
import com.desafio.dio.model.CidadeRepository;
import com.desafio.dio.model.Cliente;
import com.desafio.dio.model.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private CidadeService cidadeService;
	

	@Override
	public Iterable<Cliente> buscarTodos() {
		
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		
		String cidade = cliente.getCidade().getCep();
		Cidade cidadeEncontrada = cidadeRepository.findById(cidade).orElseGet(() -> {
			
			Cidade novaCidade = cidadeService.consultarCidade(cidade);
			cidadeRepository.save(novaCidade);
			return novaCidade;
		});
		cliente.setCidade(cidadeEncontrada);
		clienteRepository.save(cliente);
	}
}
