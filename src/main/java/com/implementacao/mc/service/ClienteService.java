package com.implementacao.mc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.implementacao.mc.DTO.ClienteDTO;
import com.implementacao.mc.domain.Cliente;

import com.implementacao.mc.exceptions.DataIntegrityException;
import com.implementacao.mc.exceptions.ObjectNotFoundException;

import com.implementacao.mc.repositories.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id ) {
		Cliente obj = repo.findById(id).get();
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Cliente.class.getName());
		}
		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		upDateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		
		 find(id);
		 try {
		 repo.deleteById(id);
		 }catch(DataIntegrityViolationException e){
			 
			 throw new DataIntegrityException("Não e possivel excluir porque ha entidades relacionadas ");
		 }
	}
	
	public List<Cliente> findAll() {
		
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getName(),objDTO.getEmail(),null,null);
	}
	
	private void upDateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}

	
	

