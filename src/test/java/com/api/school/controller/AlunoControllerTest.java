package com.api.school.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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

	@DisplayName("Controller findAll com sucesso")
	@Test
	void testFindAll() {
		when(service.findAll()).thenReturn(List.of(aluno));
		when(mapper.map(any(), any())).thenReturn(alunoDTO);
		ResponseEntity<List<AlunoDTO>> response = controller.findAll();

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(AlunoDTO.class, response.getBody().get(INDEX).getClass());

		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(NAME, response.getBody().get(INDEX).getName());
		assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
		assertEquals(SCHOOL, response.getBody().get(INDEX).getSchool());
		assertEquals(alunoDTO.getDataInicio(), response.getBody().get(INDEX).getDataInicio());
		assertEquals(alunoDTO.getDataEncerramento(), response.getBody().get(INDEX).getDataEncerramento());
	}

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

	@DisplayName("Controller save com sucesso")
	@Test
	void testSave() {
		when(service.save(any())).thenReturn(aluno);

		ResponseEntity<AlunoDTO> response = controller.save(alunoDTO);

		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getHeaders().get("Location"));
	}

	@DisplayName("Controller update com sucesso")
	@Test
	void testUpdate() {
		when(service.update(alunoDTO)).thenReturn(aluno);
		when(mapper.map(any(), any())).thenReturn(alunoDTO);
		
		ResponseEntity<AlunoDTO> response = controller.update(ID, alunoDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(AlunoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(SCHOOL, response.getBody().getSchool());
		assertEquals(aluno.getDataInicio(), response.getBody().getDataInicio());
		assertEquals(aluno.getDataEncerramento(), response.getBody().getDataEncerramento());
	}

//	@Test
//	void testDelete() {
//	}

	private void startAluno() {
		aluno = new Aluno(ID, NAME, EMAIL, SCHOOL);
		alunoDTO = new AlunoDTO(ID, NAME, EMAIL, SCHOOL);
	}

}
