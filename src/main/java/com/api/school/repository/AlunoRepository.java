package com.api.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.school.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Optional<Aluno> findByEmail(String email);
}
