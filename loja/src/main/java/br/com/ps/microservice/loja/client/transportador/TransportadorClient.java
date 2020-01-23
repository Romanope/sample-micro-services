package br.com.ps.microservice.loja.client.transportador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.ps.microservice.loja.client.transportador.dto.EntregaDTO;
import br.com.ps.microservice.loja.client.transportador.dto.VoucherDTO;

@Repository
public class TransportadorClient {

	private static final String GATEWAY_BASE_URL = "http://localhost:5555/transportador";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public VoucherDTO reservaEntrega(EntregaDTO pedidoDTO) {
		HttpEntity<EntregaDTO> request = new HttpEntity<>(pedidoDTO);
		ResponseEntity<VoucherDTO> response = restTemplate
		  .exchange(GATEWAY_BASE_URL + "/entregas", HttpMethod.POST, request, VoucherDTO.class);
		
		return response.getBody();
	}
}
