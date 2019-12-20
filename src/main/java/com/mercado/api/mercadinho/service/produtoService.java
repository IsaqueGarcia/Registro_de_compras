package com.mercado.api.mercadinho.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mercado.api.mercadinho.model.produto;
import com.mercado.api.mercadinho.repository.produtoRepository;

@Service
public class produtoService {

	@Autowired
	private produtoRepository repository;
	
	public JSONObject cadastraProduto(produto input){
		JSONObject responseJson = new JSONObject();
		try{
			if(StringUtils.isEmpty(input.getNome()) || StringUtils.isEmpty(input.getQuantidade())){
				responseJson.put("returnCode", "2");
				responseJson.put("returnDescription", "Nome e Quantidade do produto não podem ser vazios.");
				return responseJson;
			}
			repository.save(input);
			responseJson.put("returnCode", "0");
			responseJson.put("returnDescription", "Salvo com sucesso!");
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}
	
	public JSONObject buscaProdutoPeloNome(String nome){
		JSONObject responseJson = new JSONObject();
		try{
			if(StringUtils.isEmpty(nome)){
				responseJson.put("returnCode", "2");
				responseJson.put("returnDescription", "Tu quer procurar como sem colocar um nome? sua anta!");
				return responseJson;
			}
			produto pro = repository.buscaProdutoPeloNome(nome);
			if(pro == null){
				responseJson.put("returnCode", "0");
				responseJson.put("returnDescription", "Nenhum registro encontrado.");
				return responseJson;
			}
			responseJson = new JSONObject(pro);
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}
	

}
