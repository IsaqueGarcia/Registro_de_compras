package com.mercado.api.mercadinho.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mercado.api.mercadinho.Utils.Util;
import com.mercado.api.mercadinho.model.cliente;
import com.mercado.api.mercadinho.model.registrosCompra;
import com.mercado.api.mercadinho.repository.registrosCompraRepository;

@Service
public class registrosCompraService {

	@Autowired
	public registrosCompraRepository repository;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public JSONObject criarRegistro(registrosCompra input){
		JSONObject responseJson = new JSONObject();
		try{
			if(StringUtils.isEmpty(input.getIdCliente())){
				responseJson.put("returnCode", "2");
				responseJson.put("returnDescription", "Os campos cliente e produto não podem estar vazios.");
				return responseJson;
			}
			cliente cli = repository.buscaClientePeloId(input.getIdCliente());
			
			input.setCliente(cli);
			
			input.setNomeProduto(cli.getNomeProduto());
			input.setNomeCliente(cli.getNome());
			input.setPrecoUnit(cli.getPrecoUnit());
			input.setQtd(cli.getQuantidade());
			input.setPrecoTotal(cli.getQuantidade() * cli.getPrecoUnit());
			input.setDtCompra(sdf.format(new Date()));
			
			repository.saveAndFlush(input);
			responseJson.put("returnCode", "0");
			responseJson.put("returnDescription", "Salvo com sucesso!");
			
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}
	
	public JSONObject buscaRegistro(Integer id){
		JSONObject responseJson = new JSONObject();
		try{
			if(id == null){
				responseJson.put("returnCode", "2");
				responseJson.put("returnDescription", "Id do registro não deve estar vazio.");
				return responseJson;
			}
			String registro = repository.buscaRegistro(id);
			if(StringUtils.isEmpty(registro)){
				responseJson.put("returnCode", "0");
				responseJson.put("returnDescription", "Nenhum registro encontrado");
				return responseJson;
			}
			String registroArr[] = new String[6];
			registroArr = registro.split(",");
			responseJson.put("ID", registroArr[0]);
			responseJson.put("DATA_COMPRA", registroArr[1]);
			responseJson.put("PREÇO_TOTAL", "R$ " + registroArr[2]);
			responseJson.put("PREÇO_UNIT", "R$ " + registroArr[3]);
			responseJson.put("NOME_CLIENTE",registroArr[4]);
			responseJson.put("NOME_PRODUTO",registroArr[5]);
			responseJson.put("QUANTIDADE", registroArr[6]);
	
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}
	
	public JSONObject valorTotal(){
		JSONObject responseJson = new JSONObject();
		try{
			Double valorTotal = repository.valorTotal();
			if(valorTotal == null){
				responseJson.put("returnCode", "0");
				responseJson.put("returnDescription", "Nenhum valor registrado");
				return responseJson;
			}
			responseJson.put("VALOR_TOTAL","R$" + valorTotal);
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}
	
	public JSONObject geraExcel(){
		JSONObject responseJson = new JSONObject();
		try{
			List<registrosCompra> list = repository.buscaTodos();
			Double valorTotal = repository.valorTotal();
			Util.criaArquivoExcel("registros.xls", list,valorTotal);
			responseJson.put("returnCode", "0");
			responseJson.put("returnDescription", "Excel Gerado com sucesso!");
		}catch(Exception e){
			System.out.println("ERROR::" + e.getMessage());
			responseJson.put("returnCode", "1000");
			responseJson.put("returnDescription", "INTERNAL SERVER ERROR(TIME OUT)");
		}
		return responseJson;
	}

	
}
