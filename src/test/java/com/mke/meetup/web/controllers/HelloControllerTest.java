package com.mke.meetup.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mke.meetup.domain.Person;
import com.mke.meetup.services.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HelloService helloService;

    @Test
    public void rootPath_ReturnsKnownMessage() throws Exception {
        this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, world!"));
    }

    @Test
    public void hello_CallsHelloService_ReturnsAsExpected() throws Exception {
        final String name = "Jonathan";

        given(this.helloService.getMessage()).willReturn("hello");

        this.mvc.perform(get("/name").param("name", "Jonathan").accept(MediaType.TEXT_PLAIN))
                    .andExpect(status().isOk())
                    .andExpect(content().string("hello Jonathan"));
    }

    @Test
    public void createPerson_CreatesPerson_JsonResponse() throws Exception {
        Person person = new Person();
        person.setName("Jonathan");

        this.mvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json("{ \"name\": \"Jonathan\", \"id\": 1 }"));
    }

}
