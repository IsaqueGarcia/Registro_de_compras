package com.mercado.api.mercadinho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercado.api.mercadinho.model.produto;
import com.mercado.api.mercadinho.repository.produtoRepository;

@SpringBootApplication
public class MercadinhoApplication implements CommandLineRunner {
	
	@Autowired
	private produtoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MercadinhoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		produto p1 = new produto("Banana", 50, "Nanica", "N/A", null, 4.99);
		produto p2 = new produto("Manga", 50, "", "Mangas express", null, 7.99);
		produto p3 = new produto("Coco", 50, "", "N/A", null, 13.99);
		produto p4 = new produto("Limão", 50, "L", "Limões Joanes", null, 2.59);
		
		repository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
	}

}
