package br.com.ps.microservice.fornecedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ps.microservice.fornecedor.model.Fornecedor;
import br.com.ps.microservice.fornecedor.repositorio.FornecedorRepositorio;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepositorio fornecedorRepositorio;
	
	public Fornecedor obterFornecedorPorUF(String uf) {
		return fornecedorRepositorio.findByUf(uf);
	}
}
