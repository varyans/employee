package com.tzonesoft.employee.controller;

import com.tzonesoft.employee.model.ErrorResponse;
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
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_without_params() throws Exception {
        mockMvc.perform(get("/employee"))
                .andDo(print())
                .andExpect(status()
                        .is2xxSuccessful());
    }

    @Test
    void get_without_params_return_empty_employeeList() throws Exception {
        mockMvc.perform(get("/employee"))
                .andDo(print())
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void post_with_employee_it_should_return_created() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employee")
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

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());

        mockMvc.perform(get("/employee"))
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

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());
//act
        mockMvc.perform(get("/employee/1"))
                .andDo(print())

                //assert
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("ismail")
                );
    }
    @Test
    void get_name_created_object() throws Exception {
        //arrange
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());
//act
        mockMvc.perform(get("/employee?name=ismail"))
                .andDo(print())

                //assert
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("ismail")
                );
    }

    @Test
    void delete_created_object() throws Exception {
        //arrange
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print());
//act
        mockMvc.perform(delete("/employee/1"))
                .andDo(print())

                //assert
                .andExpect(status()
                        .isNoContent());
    }

    @Test
    void put_with_employee_it_should_return_created() throws Exception {
        String request = "{\"id\":1,\"name\":\"ismail\"}";

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status()
                        .isCreated())
                .andExpect(jsonPath("$.name").value("ismail")
                );

        String replace = "{\"id\":1,\"name\":\"yeşim\"}";

        mockMvc.perform(put("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(replace))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$.name").value("yeşim")
                );
    }
  @Test
  void get_created_error() throws Exception {
        //arrange
      ErrorResponse expected = new ErrorResponse("Employee Not Found", 123);
      mockMvc.perform(delete("/employee/1"))
              .andDo(print());
      //act
      mockMvc.perform(get("/employee/1"))
              .andDo(print())
              //assert
              .andExpect(status()
                      .is2xxSuccessful())
              .andExpect(jsonPath("$.message").value(expected.getMessage()))
              .andExpect(jsonPath("$.code").value(expected.getCode())
              );
  }


}