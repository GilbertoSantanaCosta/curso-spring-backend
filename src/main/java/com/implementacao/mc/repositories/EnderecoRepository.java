package com.implementacao.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.implementacao.mc.domain.Endereco;



@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
