package com.empresa.app.Student.models.service;

import org.springframework.http.ResponseEntity;

import com.empresa.app.Student.models.documents.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudenService {

	public Flux<Student> findAll();

	public Mono<Student> findById(String id);

	public Mono<Student> findByDni(String dni);

	public Flux<Student> findByName(String name);

	public Mono<Student> save(Student student);

	public Mono<Void> delete(String id);

	public Mono<ResponseEntity<Student>> update(Student student, String id);

}
