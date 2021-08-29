package com.implementacao.mc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.domain.ItemPedido;
import com.implementacao.mc.domain.PagamentoComBoleto;
import com.implementacao.mc.domain.Pedido;
import com.implementacao.mc.domain.enums.EstadoPagamento;
import com.implementacao.mc.exceptions.ObjectNotFoundException;
import com.implementacao.mc.repositories.ClienteRepository;
import com.implementacao.mc.repositories.ItemPedidoRepository;
import com.implementacao.mc.repositories.PagamentoRepository;
import com.implementacao.mc.repositories.PedidoRepository;
import com.implementacao.mc.repositories.ProdutoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id ) {
		Pedido obj = repo.findById(id).get();
           if(obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo: " + Pedido.class.getName());
		}
		
		return obj;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findById(ip.getProduto().getId()).get());
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	
}
