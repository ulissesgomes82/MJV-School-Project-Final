package com.api.school.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.school.model.Aluno;
import com.api.school.model.dto.AlunoDTO;
import com.api.school.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<AlunoDTO>> findAll() {
		return ResponseEntity.ok(service.findAll().stream().map(x->mapper.map(x, AlunoDTO.class)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(mapper.map(service.findById(id), AlunoDTO.class));
	}
	
	@PostMapping
	public ResponseEntity<AlunoDTO> save(@RequestBody @Valid AlunoDTO aluno){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.save(aluno).getId()).toUri();
		return ResponseEntity.created(uri).body(null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
		return ResponseEntity.ok(service.update(id, aluno));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
