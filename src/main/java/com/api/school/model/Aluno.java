package com.api.school.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Nome", length = 50)
	private String name;
	@Column(name = "E-mail")
	@Email
	private String email;
	@Column(name = "School", length = 50)
	private String school;
	@Column(name = "Inicio")
	private final LocalDate dataInicio = LocalDate.now();
	@Column(name = "Encerramento")
	private final LocalDate dataencerramento = LocalDate.now().plusDays(35);

	public Aluno() {
	}

	public Aluno(Long id, String name, String email, String school) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.school = school;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataencerramento() {
		return dataencerramento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id);
	}

}
