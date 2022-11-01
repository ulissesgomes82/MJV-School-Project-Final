package com.api.school.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
	private Long id;
	private String name;
	private String email;
	private String school;
	private final LocalDate dataInicio = LocalDate.now();
	private final LocalDate dataEncerramento = LocalDate.now().plusDays(35);

}
