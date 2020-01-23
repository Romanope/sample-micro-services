package br.com.ps.microservice.fornecedor.repositorio;


import org.springframework.data.repository.CrudRepository;

import br.com.ps.microservice.fornecedor.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
