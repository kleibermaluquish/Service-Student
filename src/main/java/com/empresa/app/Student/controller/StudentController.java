package com.empresa.app.Student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.app.Student.models.documents.Student;
import com.empresa.app.Student.models.service.StudenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/student")
@Api(value = "infos", description = "Infos API", produces = "application/json")
public class StudentController {

	@Autowired
	private StudenService service;

	// LISTAR ESTUDIANTES
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/listar")
	@ApiOperation(value = "Obtiene todo los estudiantes", notes = "Returns all students")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Existe al menos un estudiante")
    })
	
	public Flux<Student> listStudent() {
		Flux<Student> studentList = service.findAll();

		return studentList;
	}

	// LISTAR ESTUDIANTE POR ID
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	@ApiOperation(value = "Obtiene un estudiante por Id", notes = "Returns one student by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Existe un estudiante")
    })
	
	public Mono<Student> showById(@PathVariable String id) {
		Mono<Student> student = service.findById(id);

		return student;
	}

	// LISTAR ETUDIANTE POR DNI
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/listDni/{dni}")
	@ApiOperation(value = "Obtiene un estudiante por Dni", notes = "Returns all infos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exits one info at least")
    })

	public Mono<Student> listByDni(@ApiParam(defaultValue = "1", value = "The id of the info to return")
									@PathVariable String dni) {
		Mono<Student> listId = service.findByDni(dni);

		return listId;
	}

	// LISTAR ESTUDIANTES POR NOMBRE
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/listName/{name}")
	@ApiOperation(value = "Obtiene estudiantes por nombre", notes = "Returns all infos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exits one info at least")
    })
	
	public Flux<Student> listByName(@PathVariable String name) {
		Flux<Student> listDni = service.findByName(name);

		return listDni;
	}

	// GUARDAR UN ESTUDIANTE
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Guarda un estudiante", notes = "Returns all infos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exits one info at least")
    })

	public Mono<Student> create(@RequestBody Student student) {
		return service.save(student);
	}

	// EDITAR UN ESTUDIANTE POR ID
	
	 @PutMapping("/{id}")
	  
	 @ResponseStatus(HttpStatus.CREATED) 
	 public Mono<ResponseEntity<Student>> updateStudent(@RequestBody Student student, @PathVariable String id){
	  
	 return service.update(student, id); 
	 }
	 

	// ELIMINAR UN ESTUDIANTE POR ID
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Eliminar un estudiante por Id", notes = "Returns all infos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exits one info at least")
    })
	
	public Mono<Void> delete(@PathVariable String id) {
		Mono<Void> deletStudent = service.delete(id);

		return deletStudent;
	}

}
