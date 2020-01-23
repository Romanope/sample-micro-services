package br.com.ps.microservice.loja.client.fornecedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.ps.microservice.loja.controller.dto.FornecedorDto;
import br.com.ps.microservice.loja.controller.dto.ItemCompraDto;
import br.com.ps.microservice.loja.controller.dto.PedidoDto;

@Repository
public class FornecedorClient {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String GATEWAY_BASE_URL = "http://localhost:5555/fornecedor";
	
	public FornecedorDto getInfoEstado(String uf) {
		ResponseEntity<FornecedorDto> response = restTemplate.getForEntity(GATEWAY_BASE_URL + "/fornecedores/" + uf, FornecedorDto.class);
		return response.getBody();
	}

	public PedidoDto realizarPedido(List<ItemCompraDto> itens) {
		HttpEntity<List<ItemCompraDto>> request = new HttpEntity<>(itens);
		ResponseEntity<PedidoDto> response = restTemplate
		  .exchange(GATEWAY_BASE_URL + "/pedidos", HttpMethod.POST, request, PedidoDto.class);
		
		return response.getBody();
	}
}
