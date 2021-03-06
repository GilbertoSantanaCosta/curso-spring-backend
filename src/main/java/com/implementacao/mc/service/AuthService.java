package com.implementacao.mc.service;

import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.repositories.ClienteRepository;
import com.implementacao.mc.service.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {

			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newpass = newPassword();
		cliente.setSenha(pe.encode(newpass));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newpass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {

			vet[i] = randomChar();
		}
		return new String(vet);
	}

	// Site unicode-table.com possui toda a lista de caracteres do char
	private char randomChar() {

		int opt = rand.nextInt(3);

		if (opt == 0) {// gera um digito

			return (char) (rand.nextInt(10) + 48);
		
		}else if (opt == 1) {// gera uma letra maiuscula

			return (char) (rand.nextInt(26) + 65);
		
		}else  {// gera uma letra minuscula

			return (char) (rand.nextInt(26) + 97);
		}
		
	}
}
