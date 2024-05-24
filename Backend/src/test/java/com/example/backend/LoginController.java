package com.example.backend.Controllers;

import com.example.backend.Services.UsersService;
import com.example.backend.payload.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class LoginControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testAdminLogin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("password");

        when(usersService.checkAdminLoginDetail(any(LoginDto.class))).thenReturn("Login Successful");

        mockMvc.perform(post("/user/adminlogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login Successful"));
    }

    @Test
    public void testUserLogin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("user");
        loginDto.setPassword("password");

        when(usersService.checkUserLoginDetail(any(LoginDto.class))).thenReturn("Login Successful");

        mockMvc.perform(post("/user/userlogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login Successful"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

