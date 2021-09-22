package com.implementacao.mc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.implementacao.mc.domain.Categoria;
import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.domain.Produto;
import com.implementacao.mc.repositories.CategoriaRepository;
import com.implementacao.mc.repositories.ProdutoRepository;
import com.implementacao.mc.service.exception.ObjectNotFoundException;


@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id ) {
		Produto obj = repo.findById(id).get();
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Produto.class.getName());
		}
		
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
	
}
