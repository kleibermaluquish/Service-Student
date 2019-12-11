package com.empresa.app.Student.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.empresa.app.Student.models.documents.Student;

@Repository
public interface StudentDao extends ReactiveMongoRepository<Student, String> {

}
