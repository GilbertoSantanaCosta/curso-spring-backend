package com.implementacao.mc;


import java.text.SimpleDateFormat;
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
import com.implementacao.mc.domain.ItemPedido;
import com.implementacao.mc.domain.Pagamento;
import com.implementacao.mc.domain.PagamentoComBoleto;
import com.implementacao.mc.domain.PagamentoComCartao;
import com.implementacao.mc.domain.Pedido;
import com.implementacao.mc.domain.Produto;
import com.implementacao.mc.domain.enums.EstadoPagamento;
import com.implementacao.mc.domain.enums.TipoCliente;
import com.implementacao.mc.repositories.CategoriaRepository;
import com.implementacao.mc.repositories.CidadeRepository;
import com.implementacao.mc.repositories.ClienteRepository;
import com.implementacao.mc.repositories.EnderecoRepository;
import com.implementacao.mc.repositories.EstadoRepository;
import com.implementacao.mc.repositories.ItemPedidoRepository;
import com.implementacao.mc.repositories.PagamentoRepository;
import com.implementacao.mc.repositories.PedidoRepository;
import com.implementacao.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
				
	
		
	}
	
	

}
