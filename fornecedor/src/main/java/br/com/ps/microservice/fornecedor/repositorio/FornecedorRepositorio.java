package br.com.ps.microservice.fornecedor.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ps.microservice.fornecedor.model.Fornecedor;

public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Long> {

	Fornecedor findByUf(String uf);
}
