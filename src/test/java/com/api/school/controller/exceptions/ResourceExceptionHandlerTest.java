package com.api.school.controller.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.api.school.service.exceptions.DataIntegrationViolationException;
import com.api.school.service.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {


	private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
	private static final String OBJECT_NOT_FOUND = "object not found";
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("ResourceExceptionHandler objectNotFound")
	@Test
	void testObjectNotFound() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFound(new ObjectNotFoundException(OBJECT_NOT_FOUND), new MockHttpServletRequest());
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(OBJECT_NOT_FOUND, response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}
	@DisplayName("ResourceExceptionHandler dataIntegrityViolation")
	@Test
	void testDataIntegrityViolationException() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegrityViolationException(new DataIntegrationViolationException(E_MAIL_JA_CADASTRADO_NO_SISTEMA), new MockHttpServletRequest());
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, response.getBody().getError());
		assertEquals(400, response.getBody().getStatus());
	}

}
