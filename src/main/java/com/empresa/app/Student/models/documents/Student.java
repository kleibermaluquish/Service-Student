package com.empresa.app.Student.models.documents;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "student")
public class Student {

	@Id
	@NotNull
    @ApiModelProperty(value = "Identificación del usuario", required = true)
	private String id;
	private String firsName;
	private String lastName;
	private String gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date f_birth;
	private String typeDocument;
	private String numberDocument;
	private String familyRelative;

	public Student() {
	}

	public Student(String firsName, String lastName, String gender, Date f_birth, String typeDocument,
			String numberDocument, String familyRelative) {
		this.firsName = firsName;
		this.lastName = lastName;
		this.gender = gender;
		this.f_birth = f_birth;
		this.typeDocument = typeDocument;
		this.numberDocument = numberDocument;
		this.familyRelative = familyRelative;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getF_birth() {
		return f_birth;
	}

	public void setF_birth(Date f_birth) {
		this.f_birth = f_birth;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getNumberDocument() {
		return numberDocument;
	}

	public void setNumberDocument(String numberDocument) {
		this.numberDocument = numberDocument;
	}

	public String getFamilyRelative() {
		return familyRelative;
	}

	public void setFamilyRelative(String familyRelative) {
		this.familyRelative = familyRelative;
	}

}
