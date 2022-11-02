package com.api.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(aptionalAluno);
		Aluno response = service.findById(ID);
		assertNotNull(response);
		assertEquals(Aluno.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SCHOOL, response.getSchool());
		
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
