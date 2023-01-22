package com.example.interview;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void throws_not_found_exception_when_find_by_not_exits_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}","id")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

    }

    @Test
    public void throws_not_found_exception_when_delete_by_not_exits_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}","id")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

    }

}
