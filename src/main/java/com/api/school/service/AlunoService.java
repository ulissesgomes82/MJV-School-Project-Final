package com.api.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.school.model.Aluno;
import com.api.school.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	public List<Aluno> findAll() {
		return repository.findAll();
	}

	public Aluno findById(Long id) {
		Optional<Aluno> obj = repository.findById(id);
		return obj.get();
	}
	
	public Aluno save(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public Aluno update(Long id, Aluno aluno) {
		aluno.setId(id);
		Aluno obj = repository.getReferenceById(id);
		obj.setName(aluno.getName());
		obj.setEmail(aluno.getEmail());
		obj.setSchool(aluno.getSchool());
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
}
