package br.com.ps.microservice.loja.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompraDto {

	@JsonIgnore
	private Long compraId;
	
	private List<ItemCompraDto> itens;
	
	private EnderecoDto endereco;

	public List<ItemCompraDto> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompraDto> itens) {
		this.itens = itens;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	public Long getCompraId() {
		return compraId;
	}

	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}

	@Override
	public String toString() {
		return "CompraDto [itens=" + itens + ", endereco=" + endereco + "]";
	}
}
