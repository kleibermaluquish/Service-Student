package com.empresa.app.Student.models.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.empresa.app.Student.models.dao.StudentDao;
import com.empresa.app.Student.models.documents.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudenService {

	@Autowired
	private StudentDao dao;

	@Override
	public Flux<Student> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Student> findById(@PathVariable String id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Student> findByDni(String dni) {

		Flux<Student> listStudent = dao.findAll();
		Mono<Student> studentDni = listStudent.filter(p -> p.getNumberDocument().equals(dni)).next();

		return studentDni;
	}

	@Override
	public Flux<Student> findByName(String firstName) {

		Flux<Student> studentName = dao.findAll();
		Flux<Student> listStudentName = studentName.filter(p -> p.getFirsName().equals(firstName));

		return listStudentName;
	}

	@Override
	public Mono<Student> save(Student student) {

		return dao.save(student);
	}

	@Override
	public Mono<Void> delete(@PathVariable String id) {

		return dao.deleteById(id);
	}

	@Override
	public Mono<ResponseEntity<Student>> update(@RequestBody Student student, @PathVariable String id) {

		    return dao.findById(id).flatMap(p -> {
			p.setFirsName(student.getFirsName());
			p.setLastName(student.getLastName());
			p.setGender(student.getGender());
			p.setTypeDocument(student.getTypeDocument());
			p.setNumberDocument(student.getNumberDocument());
			p.setFamilyRelative(student.getFamilyRelative());

			return dao.save(p);
		}).map(s->ResponseEntity.created(URI.create("/api/student/".concat(s.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(s))
		    		.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	// deprecadoasdadasd

}
