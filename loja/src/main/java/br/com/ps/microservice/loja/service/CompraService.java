package br.com.ps.microservice.loja.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.ps.microservice.loja.client.fornecedor.FornecedorClient;
import br.com.ps.microservice.loja.client.transportador.TransportadorClient;
import br.com.ps.microservice.loja.client.transportador.dto.EntregaDTO;
import br.com.ps.microservice.loja.client.transportador.dto.VoucherDTO;
import br.com.ps.microservice.loja.controller.dto.CompraDto;
import br.com.ps.microservice.loja.controller.dto.FornecedorDto;
import br.com.ps.microservice.loja.controller.dto.PedidoDto;
import br.com.ps.microservice.loja.model.Compra;
import br.com.ps.microservice.loja.model.CompraState;
import br.com.ps.microservice.loja.repository.CompraRepository;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private TransportadorClient transportadorClient;

	@HystrixCommand(fallbackMethod = "realizarCompraFallback")
	public Compra realizarCompra(CompraDto compraDto) {

		Compra compra = iniciarProcessoDeEfetivacaoDeCompra(compraDto);
		
		LOG.info("Buscando dados do fornecedor do estado {} ", compraDto.getEndereco().getEstado());
		FornecedorDto fornecedor = fornecedorClient.getInfoEstado(compraDto.getEndereco().getEstado());

		PedidoDto pedido = realizarPedidoAoFornecedor(compraDto, compra);

		VoucherDTO voucher = solicitarEntregaAoTransportador(compraDto, fornecedor, pedido);

		salvarCompra(compra, pedido, voucher);

		return compra;
	}

	private Compra iniciarProcessoDeEfetivacaoDeCompra(CompraDto compraDto) {
		Compra compra = new Compra();
		compra.setState(CompraState.RECEBIDO);
		compra.setEnderecoDestino(compraDto.getEndereco().toString());
		compraRepository.save(compra);
		compraDto.setCompraId(compra.getId());
		return compra;
	}

	private void salvarCompra(Compra compra, PedidoDto pedido, VoucherDTO voucher) {
		LOG.info("Compra efetivada com sucesso {}", pedido);
		compra.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compra.setVoucher(voucher.getNumero());
		compra.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
		compraRepository.save(compra);
	}

	private PedidoDto realizarPedidoAoFornecedor(CompraDto compraDto, Compra compra) {
		LOG.info("Dados do fornecedor recebido, iniciando solicação da efetivação do pedido.");
		PedidoDto pedido = fornecedorClient.realizarPedido(compraDto.getItens());
		compra.setState(CompraState.PEDIDO_REALIZADO);
		compra.setPedidoId(pedido.getId());
		compra.setTempoDePreparo(pedido.getTempoDePreparo());
		compraRepository.save(compra);
		return pedido;
	}

	private VoucherDTO solicitarEntregaAoTransportador(CompraDto compraDto, FornecedorDto fornecedor,
			PedidoDto pedido) {
		return transportadorClient
				.reservaEntrega(new EntregaDTO(pedido.getId(), LocalDate.now().plusDays(pedido.getTempoDePreparo()),
						fornecedor.enderecoAsString(), compraDto.getEndereco().toString()));
	}

	public Compra realizarCompraFallback(CompraDto compraDto) {

		if (compraDto.getCompraId() != null) {
			return compraRepository.findById(compraDto.getCompraId()).get();
		}

		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compraDto.getEndereco().toString());
		return compraFallback;
	}
}
