package com.implementacao.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.implementacao.mc.domain.ItemPedido;
import com.implementacao.mc.domain.ItemPedidoPK;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,  ItemPedidoPK> {

}
