package com.empresa.app.Student;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.empresa.app.Student.models.dao.StudentDao;
import com.empresa.app.Student.models.documents.Student;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private StudentDao dao;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("student").subscribe();
		
		
		Flux.just(
			new Student("Jose", "Rojas", "M", new Date(), "dni", "70090978", "hermanos"),
			new Student("Ana", "Martinez", "F", new Date(), "dni", "11223344", "padres"),
			new Student("Raul", "Rojas", "M", new Date(), "dni", "22334455", "padres"),
			new Student("Juan", "Vasquez", "M", new Date(), "dni", "55443322", "padres"),
			new Student("Sofia", "Flores", "F", new Date(), "dni", "55446662", "padres"),
			new Student("Sofia", "sandoval", "F", new Date(), "dni", "33333333", "padres"),
			new Student("Raul", "Torivio", "M", new Date(), "dni", "11111111", "padres"),
			new Student("Raul", "Martinez", "M", new Date(), "dni", "55555555", "padres"),
			new Student("Gian", "Torres", "M", new Date(), "dni", "55555555", "padres")
				)
		.flatMap(student -> dao.save(student))
		.subscribe(student -> log.info("Id: "+student.getId()+ "Nombre: "+student.getFirsName()));
		
	}

}
