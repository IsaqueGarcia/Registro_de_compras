package com.mercado.api.mercadinho.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mercado.api.mercadinho.model.cliente;
import com.mercado.api.mercadinho.model.produto;
import com.mercado.api.mercadinho.repository.clienteRepository;
import com.mercado.api.mercadinho.repository.produtoRepository;

@Service
public class clienteService {

	@Autowired
	private clienteRepository repository;
	
	@Autowired
	private produtoRepository produtoRepo;
	
	public JSONObject cadastraCliente(cliente input){
		JSONObject responseJson = new JSONObject();
		try{
			if(StringUtils.isEmpty(input.getNome())){
				responseJson.put("returnCode", "2");
				responseJson.put("returnDescription", "O nome não pode estar vazio.");
				return responseJson;
			}
			produto p = produtoRepo.buscaProdutoPeloNome(input.getNomeProduto());
			input.setPrecoUnit(p.getPreco());
			p.setQuantidade(p.getQuantidade() - input.getQuantidade());
			produtoRepo.save(p);
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
	
	public JSONObject buscaCliente(Integer id){
		JSONObject responseJson = new JSONObject();
		try{
			if(id == null){
				responseJson.put("returnCode", "2");
				responseJson.put("returnDescription", "O id não pode ser passado como vazio.");
				return responseJson;
			}
			cliente cli = repository.buscaCliente(id);
			if(cli == null){
				responseJson.put("returnCode", "0");
				responseJson.put("returnDescription", "Não ha nenhum cliente registrado com esse id!");
				return responseJson;
			}
			responseJson.put("Nome", cli.getNome());
			responseJson.put("Documento", cli.getNumeroDocumento());
			responseJson.put("Registro", cli.getRegistro());
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}
	
	
}
