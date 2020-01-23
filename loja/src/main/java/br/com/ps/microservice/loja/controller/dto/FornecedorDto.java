package br.com.ps.microservice.loja.controller.dto;

public class FornecedorDto {

private String razaoSocial;
	
	private String cnpjCpf;
	
	private String rua;
	
	private String numero;
	
	private String cidade;
	
	private String uf;
	
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
	
	public String enderecoAsString() {
		return new StringBuilder(rua).
				append(", ").
				append(numero).
				append(", ").
				append(cidade).
				append(", ").
				append(uf).toString();
	}
}
