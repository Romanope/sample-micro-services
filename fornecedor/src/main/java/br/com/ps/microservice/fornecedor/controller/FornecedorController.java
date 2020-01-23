package br.com.ps.microservice.fornecedor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ps.microservice.fornecedor.controller.dto.FornecedorDto;
import br.com.ps.microservice.fornecedor.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	private static final Logger LOG = LoggerFactory.getLogger(FornecedorController.class);

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping("/{uf}")
	public ResponseEntity<FornecedorDto> getInfoEstado(@PathVariable String uf) {
		LOG.info("Recebida requisição para o fornecedor do estado {}", uf);
		return ResponseEntity.ok(new FornecedorDto(fornecedorService.obterFornecedorPorUF(uf)));
	}
}
