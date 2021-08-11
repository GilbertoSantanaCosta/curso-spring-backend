package com.implementacao.mc.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import org.hibernate.validator.constraints.Length;

import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.service.validation.ClienteUpdate;


@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5,max=120,message="O tamanho deve ser entre 5 e 120 caracteres")
	String nome;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message="Email inválido")
	String email;
	
	public ClienteDTO() {
		
	}
	
    public ClienteDTO(Cliente obj) {
		
    	id = obj.getId();
    	nome = obj.getNome();
    	email = obj.getEmail();
    			
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String name) {
		this.nome = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
