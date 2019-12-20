package com.mercado.api.mercadinho.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercado.api.mercadinho.model.cliente;
import com.mercado.api.mercadinho.service.clienteService;

@Controller
public class clienteController {
	
	@Autowired
	public clienteService service;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "salva/cliente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> salvaCliente(@RequestBody cliente input){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.cadastraCliente(input);
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "busca/cliente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> buscaCliente(@PathVariable Integer id){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.buscaCliente(id);
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}
}
