package com.implementacao.mc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.implementacao.mc.DTO.ClienteDTO;
import com.implementacao.mc.DTO.ClienteNewDTO;

import com.implementacao.mc.domain.Cidade;
import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.domain.Endereco;
import com.implementacao.mc.domain.enums.TipoCliente;
import com.implementacao.mc.exceptions.DataIntegrityException;
import com.implementacao.mc.exceptions.ObjectNotFoundException;
import com.implementacao.mc.repositories.CidadeRepository;
import com.implementacao.mc.repositories.ClienteRepository;
import com.implementacao.mc.repositories.EnderecoRepository;


@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository; 
	
	public Cliente find(Integer id ) {
		Cliente obj = repo.findById(id).get();
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Cliente.class.getName());
		}
		
		return obj;
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return  obj;
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
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli =  new Cliente(null,objDTO.getNome(),objDTO.getEmail(),objDTO.getCnpjOuCpf(),TipoCliente.toEnum(objDTO.getTipo()),pe.encode(objDTO.getSenha()));
		Cidade cid = cidadeRepository.findById(objDTO.getCidadeId()).get();
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		 return cli;
	}
	
	private void upDateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}

	
	

