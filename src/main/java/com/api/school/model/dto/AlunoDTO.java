package com.api.school.model.dto;

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

}
