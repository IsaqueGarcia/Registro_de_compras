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

import com.mercado.api.mercadinho.Utils.Util;
import com.mercado.api.mercadinho.model.registrosCompra;
import com.mercado.api.mercadinho.service.registrosCompraService;

@Controller
public class registrosCompraController {
	
	@Autowired
	public registrosCompraService service;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "cria/registro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> criaRegistro(@RequestBody registrosCompra input){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.criarRegistro(input);
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String code = responseJson.isNull("returnCode") ? "1000" : responseJson.getString("returnCode");
		return new ResponseEntity<String>(responseJson.toString(), Util.returnCode(code));
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "busca/registro/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> buscaRegistro(@PathVariable Integer id){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.buscaRegistro(id);
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String code = responseJson.isNull("returnCode") ? "1000" : responseJson.getString("returnCode");
		return new ResponseEntity<String>(responseJson.toString(), Util.returnCode(code));
	}
	
	@RequestMapping(value = "busca/valorTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> buscaRegistro(){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.valorTotal();
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String code = responseJson.isNull("returnCode") ? "1000" : responseJson.getString("returnCode");
		return new ResponseEntity<String>(responseJson.toString(), Util.returnCode(code));
	}
	
	@RequestMapping(value = "gerarExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> geralExcel(){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.geraExcel();
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String code = responseJson.isNull("returnCode") ? "1000" : responseJson.getString("returnCode");
		return new ResponseEntity<String>(responseJson.toString(), Util.returnCode(code));
	}
	

}
