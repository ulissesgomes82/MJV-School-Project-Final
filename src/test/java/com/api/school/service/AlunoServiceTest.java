package com.api.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.school.model.Aluno;
import com.api.school.model.dto.AlunoDTO;
import com.api.school.repository.AlunoRepository;
import com.api.school.service.exceptions.DataIntegrationViolationException;
import com.api.school.service.exceptions.ObjectNotFoundException;

@SpringBootTest
class AlunoServiceTest {

	private static final int INDEX = 0;
	private static final String SCHOOL = "School - Java";
	private static final String EMAIL = "ulisses@gmail.com";
	private static final String NAME = "Ulisses";
	private static final long ID = 1L;
	@InjectMocks
	private AlunoService service;
	@Mock
	private AlunoRepository repository;
	@Mock
	private ModelMapper mapper;

	private Aluno aluno;
	private AlunoDTO alunoDTO;
	private Optional<Aluno> optionalAluno;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		MockitoAnnotations.openMocks(this);
		startAluno();
	}

	@DisplayName("Teste retorno da lista de Aluno")
	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(aluno));
		List<Aluno> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Aluno.class, response.get(INDEX).getClass());
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NAME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(SCHOOL, response.get(INDEX).getSchool());
		assertEquals(aluno.getDataInicio(), response.get(INDEX).getDataInicio());
		assertEquals(aluno.getDataEncerramento(), response.get(INDEX).getDataEncerramento());
	}

	@DisplayName("Teste retorna aluno por Id")
	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optionalAluno);
		Aluno response = service.findById(ID);
		assertNotNull(response);
		assertEquals(Aluno.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SCHOOL, response.getSchool());

	}

	@DisplayName("Teste de exceções de objeto não encontrado")
	@Test
	void findByIdObjectNotFoundException() {
		when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException("object not found"));

		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("object not found", e.getMessage());
		}
	}

	@DisplayName("Teste aluno salvo com sucesso")
	@Test
	void testSave() {
		when(repository.save(any())).thenReturn(aluno);
		Aluno response = service.save(alunoDTO);
		assertNotNull(response);
		assertEquals(Aluno.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SCHOOL, response.getSchool());
		assertEquals(aluno.getDataInicio(), response.getDataInicio());
		assertEquals(aluno.getDataEncerramento(), response.getDataEncerramento());
	}

	@DisplayName("Teste violação de integridade de dados ao salvar")
	@Test
	void SaveDataIntegrationViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(optionalAluno);
		try {
			optionalAluno.get().setId(2L);
			service.save(alunoDTO);
		} catch (Exception e) {
			assertEquals(DataIntegrationViolationException.class, e.getClass());
			assertEquals("E-mail já cadastrado no sistema", e.getMessage());
		}
	}

//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}

	private void startAluno() {
		aluno = new Aluno(ID, NAME, EMAIL, SCHOOL);
		alunoDTO = new AlunoDTO(ID, NAME, EMAIL, SCHOOL);
		optionalAluno = Optional.of(new Aluno(ID, NAME, EMAIL, SCHOOL));
	}
}
