package com.mercado.api.mercadinho.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpStatus;

import com.mercado.api.mercadinho.model.registrosCompra;

public class Util {

	public static HttpStatus returnCode(String code){
		Integer codeParse = Integer.parseInt(code);
		if(codeParse == 0){
			return HttpStatus.OK;
		}else if(codeParse > 0 &&  codeParse <= 10 ){
			return HttpStatus.UNPROCESSABLE_ENTITY;
		}else{
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	
	public static void criaArquivoExcel(String nomeArquivo,List<registrosCompra> registro,Double valorTotal) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet primeiraFolha = workbook.createSheet("PrimeiraAba");
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(new File(nomeArquivo));
			int incrementa = 1;
			
			HSSFRow row = primeiraFolha.createRow(0);
			row.createCell(0).setCellValue("NOME_CLIENTE");
			row.getCell(0).setCellStyle(style);
			row.createCell(1).setCellValue("NOME_PRODUTO");
			row.getCell(1).setCellStyle(style);
			row.createCell(2).setCellValue("DATA_COMPRA");
			row.getCell(2).setCellStyle(style);
			row.createCell(3).setCellValue("QUANTIDADE");
			row.getCell(3).setCellStyle(style);
			row.createCell(4).setCellValue("PREÇO_UNIT");
			row.getCell(4).setCellStyle(style);
			row.createCell(5).setCellValue("PREÇO_TOTAL");
			row.getCell(5).setCellStyle(style);
			row.createCell(6).setCellValue("VALOR_TOTAL_REG");
			row.getCell(6).setCellStyle(style);
			
		
			
			for(registrosCompra x : registro){
				HSSFRow row1 = primeiraFolha.createRow(incrementa);
					row1.createCell(0).setCellValue(x.getNomeCliente());
					row1.createCell(1).setCellValue(x.getNomeProduto());
					row1.createCell(2).setCellValue(x.getDtCompra());
					row1.createCell(3).setCellValue(x.getQtd());
					row1.createCell(4).setCellValue("R$" + x.getPrecoUnit());
					row1.createCell(5).setCellValue("R$" + x.getPrecoTotal());
					if(incrementa == 1){
					row1.createCell(6).setCellValue("R$" + valorTotal);
					}
					incrementa = incrementa + 1;
			}			
			
			
			int quantidadeColunas = primeiraFolha.getRow(0).getPhysicalNumberOfCells();
			for(int i = 0; i < quantidadeColunas; i++){
				primeiraFolha.autoSizeColumn(i);
			}
			
			workbook.write(fos);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao exportar arquivo");
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
