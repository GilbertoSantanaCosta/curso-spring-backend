package com.implementacao.mc.DTO;

import java.io.Serializable;

import com.implementacao.mc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 Integer id;
     String nome;

	CategoriaDTO() {

	}
	
	public CategoriaDTO(Categoria obj) {
		
		id = obj.getId();
		nome = obj.getName();
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

	public void setNome(String nome) {
		this.nome = nome;
	}

}
