package com.implementacao.mc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.implementacao.mc.domain.Pedido;
import com.implementacao.mc.exceptions.ObjectNotFoundException;

import com.implementacao.mc.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id ) {
		Pedido obj = repo.findById(id).get();
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Pedido.class.getName());
		}
		
		return obj;
	}
	
	
}
