package com.api.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
import com.api.school.service.exceptions.ObjectNotFoundException;

@SpringBootTest
class AlunoServiceTest {

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
	private Optional<Aluno> aptionalAluno;
	
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		MockitoAnnotations.openMocks(this);
		startAluno();
	}

//	@Test
//	void testFindAll() {
//		fail("Not yet implemented");
//	}

	@DisplayName("Teste retorno de instância de usuário")
	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(aptionalAluno);
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

//	@Test
//	void testSave() {
//		fail("Not yet implemented");
//	}
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
		aptionalAluno = Optional.of(new Aluno(ID, NAME, EMAIL, SCHOOL));
	}
}
