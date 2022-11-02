package com.api.school.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api.school.model.Aluno;
import com.api.school.model.dto.AlunoDTO;
import com.api.school.service.AlunoService;

@SpringBootTest
class AlunoControllerTest {

	private static final int INDEX = 0;
	private static final String SCHOOL = "School - Java";
	private static final String EMAIL = "ulisses@gmail.com";
	private static final String NAME = "Ulisses";
	private static final long ID = 1L;

	private Aluno aluno;
	private AlunoDTO alunoDTO;

	@InjectMocks
	private AlunoController controller;
	@Mock
	private AlunoService service;
	@Mock
	private ModelMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startAluno();
	}

//	@Test
//	void testFindAll() {
//	}

	@DisplayName("Controller findById com sucesso")
	@Test
	void testFindById() {
		when(service.findById(anyLong())).thenReturn(aluno);
		when(mapper.map(any(), any())).thenReturn(alunoDTO);
		ResponseEntity<AlunoDTO> response = controller.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(AlunoDTO.class, response.getBody().getClass());
	}

//	@Test
//	void testSave() {
//	}
//
//	@Test
//	void testUpdate() {
//	}
//
//	@Test
//	void testDelete() {
//	}

	private void startAluno() {
		aluno = new Aluno(ID, NAME, EMAIL, SCHOOL);
		alunoDTO = new AlunoDTO(ID, NAME, EMAIL, SCHOOL);
	}

}
