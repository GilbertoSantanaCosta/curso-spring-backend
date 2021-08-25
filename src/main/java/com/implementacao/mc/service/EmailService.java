package com.implementacao.mc.service;

import org.springframework.mail.SimpleMailMessage;

import com.implementacao.mc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
