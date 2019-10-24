package com.example.unittestdemo.controller;

import com.example.unittestdemo.constant.CommonConstant;
import com.example.unittestdemo.domain.Nasabah;
import com.example.unittestdemo.request.NasabahRequest;
import com.example.unittestdemo.response.GetListNasabahResponse;
import com.example.unittestdemo.service.impl.NasabahService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NasabahControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NasabahService nasabahService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void findAllAuthenticated() throws Exception {
        Nasabah nasabah = new Nasabah("test", "Test", 33);
        GetListNasabahResponse response = new GetListNasabahResponse();
        response.setCode(CommonConstant.OK_CODE);
        response.setData(Arrays.asList(nasabah));
        response.setMessage(CommonConstant.OK);
        when(nasabahService.findAll()).thenReturn(response);

        mockMvc.perform(get("/nasabah/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(response)));
    }

    @Test
    public void findAllUnauthenticated() throws Exception {
        mockMvc.perform(get("/nasabah/all")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password="admin")
    public void testInsert() throws Exception {
        NasabahRequest request = new NasabahRequest("test", "test", 22);
        mockMvc.perform(post("/nasabah/insert")
                .contentType("application/json")
                .content(mapper.writeValueAsString(request))
                .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(username = "admin", password="admin")
    public void testUpdate() throws Exception {
        NasabahRequest request = new NasabahRequest("test", "test2", 22);
        mockMvc.perform(put("/nasabah/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(request))
                .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "admin", password="admin")
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/nasabah/delete/test"))
                .andExpect(status().isOk());

    }


}
