package com.nttdata.client;

import com.nttdata.client.document.BussinessPerson;
import com.nttdata.client.document.Client;
import com.nttdata.client.document.DocumentType;
import com.nttdata.client.document.NaturalPerson;
import com.nttdata.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class ProyectNttdataBankApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(ProyectNttdataBankApplication.class);
	@Autowired
	ClientService clientService;
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;
	public static void main(String[] args) {
		SpringApplication.run(ProyectNttdataBankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactiveMongoTemplate.dropCollection("Client");
		reactiveMongoTemplate.dropCollection("DocumentType");

		
		BussinessPerson personal = new BussinessPerson("personal");
		BussinessPerson empresarial = new BussinessPerson("Empresarial");
		BussinessPerson tarjeta = new BussinessPerson("Tarjeta de Credito");

		NaturalPerson persona1 = new NaturalPerson("Clever","Salvador",new Date());
		NaturalPerson persona2 = new NaturalPerson("Oscar","velazco",new Date());
		NaturalPerson persona3 = new NaturalPerson("juan","nima",new Date());

		DocumentType ruc = new DocumentType("1","RUC");
		DocumentType dni = new DocumentType("2","DNI");

		Client client1 = new Client(null, "47924858", "Av. chimpu 444", ruc, persona1, null);
		Client client2 = new Client(null, "47924859", "Av. chimpu 442", ruc, persona2, null);
		Client client3 = new Client(null, "47924860", "Av. chimpu 441", ruc, persona3, null);

		Flux.just(client1, client2, client3)
				.flatMap(c -> clientService.save(c))
				.doOnNext(c -> log.info("Cliente agregado :" + c.toString()))
				.subscribe();


	}
}
