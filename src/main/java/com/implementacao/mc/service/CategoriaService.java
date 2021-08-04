package com.implementacao.mc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.implementacao.mc.DTO.CategoriaDTO;
import com.implementacao.mc.domain.Categoria;
import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.domain.Categoria;
import com.implementacao.mc.exceptions.DataIntegrityException;
import com.implementacao.mc.exceptions.ObjectNotFoundException;
import com.implementacao.mc.repositories.CategoriaRepository;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id ) {
		Categoria obj = repo.findById(id).get();
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Categoria.class.getName());
		}
		
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		upDateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		
		 find(id);
		 try {
		 repo.deleteById(id);
		 }catch(DataIntegrityViolationException e){
			 
			 throw new DataIntegrityException("Não e possivel excluir categoria que possui produto");
		 }
	}
	
	public List<Categoria> findAll() {
		
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getName());
	}
	
	private void upDateData(Categoria newObj, Categoria obj) {
		
		newObj.setName(obj.getName());
		
	}
	
}
