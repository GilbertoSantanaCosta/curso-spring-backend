package com.implementacao.mc.service.validation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.implementacao.mc.DTO.ClienteNewDTO;
import com.implementacao.mc.domain.enums.TipoCliente;
import com.implementacao.mc.exceptions.FieldMessage;
import com.implementacao.mc.service.validation.utils.CpfCnpjValidator;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	CpfCnpjValidator documento = new CpfCnpjValidator();;
	
	@Override
	 public void initialize(ClienteInsert ann) {
	 }
	 @Override
	 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();
	System.out.println(objDto.getCnpjOuCpf()); 
	 
	 if(  documento.isCPF(objDto.getCnpjOuCpf()) == false && objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()  )) {
		 list.add(new FieldMessage("cnpjOuCpf", "CPF invalido"));
	 }
	 
	if(  documento.isCNPJ(objDto.getCnpjOuCpf()) == false && objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod() )) {
		 list.add(new FieldMessage("cnpjOuCpf", "CNPJ invalido"));
	 }
	 
	 for (FieldMessage e : list) {
	 context.disableDefaultConstraintViolation();
	 context.buildConstraintViolationWithTemplate(e.getMessage())
	 .addPropertyNode(e.getFieldName()).addConstraintViolation();
	 }
	 return list.isEmpty();
	 }
	}