package br.com.ps.microservice.loja.controller.dto;

public class ItemCompraDto {

	private Long id;
	
	private double quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
}
