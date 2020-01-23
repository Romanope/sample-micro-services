package br.com.ps.microservice.fornecedor.controller.dto;

import br.com.ps.microservice.fornecedor.model.Fornecedor;

public class FornecedorDto {

private String razaoSocial;
	
	private String cnpjCpf;
	
	private String rua;
	
	private String numero;
	
	private String cidade;
	
	private String uf;
	
	public FornecedorDto(Fornecedor fornecedor) {
		this.razaoSocial = fornecedor.getRazaoSocial();
		this.rua = fornecedor.getRua();
		this.numero = fornecedor.getNumero();
		this.cidade = fornecedor.getCidade();
		this.uf = fornecedor.getUf();
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
