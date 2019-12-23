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
import com.mercado.api.mercadinho.model.produto;
import com.mercado.api.mercadinho.service.produtoService;

@Controller
public class produtoController {

	@Autowired
	public produtoService service;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "salva/produto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> salvaProduto(@RequestBody produto input){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.cadastraProduto(input);
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String code = responseJson.isNull("returnCode") ? "1000" : responseJson.getString("returnCode");
		return new ResponseEntity<String>(responseJson.toString(), Util.returnCode(code));
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "busca/produto/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<String> buscaProduto(@PathVariable String nome){
		JSONObject responseJson = new JSONObject();
		try{
			responseJson = service.buscaProdutoPeloNome(nome);
		}catch(Exception e){
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription","INTERNAL SERVER ERROR (TIME OUT)");
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String code = responseJson.isNull("returnCode") ? "1000" : responseJson.getString("returnCode");
		return new ResponseEntity<String>(responseJson.toString(), Util.returnCode(code));
	}
}
