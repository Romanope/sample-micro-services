package br.com.ps.microservice.loja.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ps.microservice.loja.controller.dto.CompraDto;
import br.com.ps.microservice.loja.model.Compra;
import br.com.ps.microservice.loja.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	@PostMapping
	public ResponseEntity<Compra> realizarCompra(@RequestBody CompraDto compraDto, UriComponentsBuilder uriBuilder) {
		Compra dadosCompra = compraService.realizarCompra(compraDto);
		
		URI uri = uriBuilder.path("/compras/{id}").buildAndExpand(dadosCompra.getId()).toUri();
		return ResponseEntity.created(uri).body(dadosCompra);
	}
}
