package com.kotak.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldCreateUserSuccessfully() throws Exception {

		String request = """
        {
          "name": "Suchit",
          "email": "suchit@gmail.com"
        }
        """;

		mockMvc.perform(
						post("/api/users")
								.contentType(MediaType.APPLICATION_JSON)
								.content(request)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("Suchit"));
	}

}
