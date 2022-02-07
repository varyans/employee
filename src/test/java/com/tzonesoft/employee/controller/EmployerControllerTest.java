package com.tzonesoft.employee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_without_params_return_empty_employerList() throws Exception {
        mockMvc.perform(get("/employer"))
                .andDo(print())
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void post_with_employee_it_should_return_created() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status()
                        .isCreated())
                .andExpect(jsonPath("$.name").value("ismail")
                );
    }

    @Test
    void get_created_object() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());

        mockMvc.perform(get("/employer"))
                .andDo(print())
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("ismail")
                );
    }

    @Test
    void get_id_created_object() throws Exception {
        //arrange
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());
//act
        mockMvc.perform(get("/employer/1"))
                .andDo(print())

                //assert
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("ismail")
                );
    }

    @Test
    void delete_created_object() throws Exception {
        //arrange
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());
//act
        mockMvc.perform(delete("/employer/1"))
                .andDo(print())

                //assert
                .andExpect(status()
                        .isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }
    @Test
    void put_with_employer_without_header_it_should_return_error_response() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())

                .andExpect(status()
                        .isCreated())
                .andExpect(jsonPath("$.name").value("ismail")
                );

        String replace = "{\"id\":1,\"name\":\"yeşim\"}";

        mockMvc.perform(put("/employer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(replace))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$.code").value("124"))
                .andExpect(jsonPath("$.message").value("Not Authenticated")
                );
    }

    @Test
    void put_with_employer_it_should_return_created() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))

                .andDo(print())

                .andExpect(status()
                        .isCreated())
                .andExpect(jsonPath("$.name").value("ismail")
                );

        String replace = "{\"id\":1,\"name\":\"yeşim\"}";

        mockMvc.perform(put("/employer/1")
                        .header("X-AUTH-TOKEN","şifre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(replace))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$.name").value("yeşim")
                );
    }
    @Test
    void put_employer_it_should_return_created() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))

                .andDo(print())

                .andExpect(status()
                        .isCreated())
                .andExpect(jsonPath("$.name").value("ismail")
                );

        String replace = "{\"id\":1,\"name\":\"yeşim\"}";

        mockMvc.perform(put("/employer/1")
                        .header("X-AUTH-TOKEN","xvyss")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(replace))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$.code").value("124"))
                .andExpect(jsonPath("$.message").value("Not Authenticated")
                );
    }
}
