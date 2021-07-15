package com.implementacao.mc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implementacao.mc.domain.Categoria;
import com.implementacao.mc.exceptions.ObjectNotFoundException;
import com.implementacao.mc.repositories.CategoriaRepository;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id ) {
		Categoria obj = repo.findOne(id);
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Categoria.class.getName());
		}
		
		return obj;
	}
	
	
}
