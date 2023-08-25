package com.examples.springboot.webflux.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.examples.springboot.webflux.app.models.dao.ProductoDao;
import com.examples.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private ProductoDao dao;
	
	//private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//mongoTemplate.dropCollection("productos").subscribe();
		
		Flux.just(new Producto("Tv samnsumg ", 2254.89),
				new Producto("microondas samnsumg ", 2254.89),
				new Producto("apple samnsumg ", 2254.89),
				new Producto("impresora samnsumg ", 2254.89),
				new Producto("camara samnsumg ", 2254.89)
				)
		.flatMap(producto ->{ 
		producto.setCreateAt(new Date());
		return dao.save(producto);
				})
		.subscribe(producto -> log.info("insert:" + producto.getId()+" "+ producto.getNombre()));
	}

}






