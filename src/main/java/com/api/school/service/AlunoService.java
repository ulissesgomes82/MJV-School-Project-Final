package com.api.school.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.school.model.Aluno;
import com.api.school.model.dto.AlunoDTO;
import com.api.school.repository.AlunoRepository;
import com.api.school.service.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public List<Aluno> findAll() {
		return repository.findAll();
	}

	public Aluno findById(Long id) {
		Optional<Aluno> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("object not found"));
	}
	
	public Aluno save(AlunoDTO aluno) {
		return repository.save(mapper.map(aluno, Aluno.class));
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
