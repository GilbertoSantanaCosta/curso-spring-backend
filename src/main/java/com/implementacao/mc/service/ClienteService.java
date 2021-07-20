package com.implementacao.mc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.exceptions.ObjectNotFoundException;

import com.implementacao.mc.repositories.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id ) {
		Cliente obj = repo.findOne(id);
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Cliente.class.getName());
		}
		
		return obj;
	}
	
	
}
