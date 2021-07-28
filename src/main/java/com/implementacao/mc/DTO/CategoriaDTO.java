package com.implementacao.mc.DTO;

import java.io.Serializable;

import com.implementacao.mc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 Integer id;
     String name;

	CategoriaDTO() {

	}
	
	public CategoriaDTO(Categoria obj) {
		
		id = obj.getId();
		name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
