package com.api.school.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.school.model.Aluno;
import com.api.school.model.dto.AlunoDTO;
import com.api.school.repository.AlunoRepository;
import com.api.school.service.exceptions.DataIntegrationViolationException;
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
		findByEmail(aluno);
		return repository.save(mapper.map(aluno, Aluno.class));
	}
	
	public Aluno update(AlunoDTO aluno) {
		findByEmail(aluno);
		return repository.save(mapper.map(aluno, Aluno.class));
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	private void findByEmail(AlunoDTO obj) {
		Optional<Aluno> aluno = repository.findByEmail(obj.getEmail());
		if (aluno.isPresent() && !aluno.get().getId().equals(obj.getId())) {
			throw new DataIntegrationViolationException("E-mail já cadastrado no sistema");
		}
	}
}
