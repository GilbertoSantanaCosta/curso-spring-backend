package com.implementacao.mc;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.implementacao.mc.domain.Categoria;
import com.implementacao.mc.domain.Cidade;
import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.domain.Endereco;
import com.implementacao.mc.domain.Estado;
import com.implementacao.mc.domain.Produto;
import com.implementacao.mc.domain.enums.TipoCliente;
import com.implementacao.mc.repositories.CategoriaRepository;
import com.implementacao.mc.repositories.CidadeRepository;
import com.implementacao.mc.repositories.ClienteRepository;
import com.implementacao.mc.repositories.EnderecoRepository;
import com.implementacao.mc.repositories.EstadoRepository;
import com.implementacao.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtosRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteReposistory;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");
		
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2 = new Produto(null,"impressora",800.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCetegoria().addAll(Arrays.asList(cat1));
		p2.getCetegoria().addAll(Arrays.asList(cat1,cat2));
		p3.getCetegoria().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtosRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Gilberto", "g.s.d.c@hotmail.com", "38733323907", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "rua dos abreus", "231", "casa", "jardim das palmeiras", "06390077", cl1, c2);
		Endereco e2 = new Endereco(null, "Av. Barão de Itapura", "34", "casa", "Vila Angelino Rossi", "13073-300", cl1, c3);
		
		
		cl1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cl1.getTelefones().addAll(Arrays.asList("98647-7263","95363-8372"));
		
		
		clienteReposistory.save(Arrays.asList(cl1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		
	}
	
	

}
