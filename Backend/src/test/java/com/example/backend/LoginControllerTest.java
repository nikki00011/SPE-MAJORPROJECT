package com.example.backend;

import com.example.backend.Controllers.LoginController;
import com.example.backend.Entities.Users;
import com.example.backend.payload.LoginDto;
import com.example.backend.payload.ResponseDto;
import com.example.backend.Services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckLogin_ReturnsSuccessMessage_Successfully() {
        // Mocking the loginDto
        LoginDto loginDto = new LoginDto("John", "john@example.com", "password");

        // Stubbing the behavior of the usersService
        when(usersService.checkAdminLoginDetail(loginDto)).thenReturn("Admin Successfully Login");

        // Calling the checkLogin method
        ResponseEntity<?> responseEntity = loginController.checkLogin(loginDto);

        // Asserting the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Asserting the response body
        String responseBody = (String) responseEntity.getBody();
        assertEquals("Admin Successfully Login", responseBody);
    }

    @Test
    public void testRegisterAdmin() {
        LoginDto loginDto = new LoginDto("admin", "admin@example.com", "password");
        String expectedResponse = "Admin register successfully";

        when(usersService.saveUserasAdmin(loginDto)).thenReturn("Admin register successfully");

        ResponseEntity<?> responseEntity = loginController.registerAdmin(loginDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(usersService, times(1)).saveUserasAdmin(loginDto);
    }

    @Test
    public void testUserLogin() {
        LoginDto loginDto = new LoginDto("username", "user@example.com", "password");
        ResponseDto expectedResponse = new ResponseDto("User Successfully Login", 1L);

        when(usersService.checkUserLoginDetail(loginDto)).thenReturn(expectedResponse);

        ResponseEntity<?> responseEntity = loginController.userLogin(loginDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(usersService, times(1)).checkUserLoginDetail(loginDto);
    }

    @Test
    public void testRegisterUser() {
        LoginDto loginDto = new LoginDto("user", "user@example.com", "password");
        String expectedResponse = "User register successfully";

        when(usersService.saveUserasUser(loginDto)).thenReturn("User register successfully");

        ResponseEntity<?> responseEntity = loginController.registerUser(loginDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(usersService, times(1)).saveUserasUser(loginDto);
    }

    @Test
    void showUsers_ReturnsListOfUsers_Successfully() {
        // Mocking the list of users
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users(1L, "John", "john@example.com", "password", 0, null, null));
        usersList.add(new Users(2L, "Alice", "alice@example.com", "password", 0, null, null));

        // Stubbing the behavior of the usersService
        when(usersService.getAllUser()).thenReturn(usersList);

        // Calling the showUsers method
        ResponseEntity<?> responseEntity = loginController.showUsers();

        // Asserting the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Asserting the response body
        List<Users> responseUsersList = (List<Users>) responseEntity.getBody();
        assertEquals(usersList.size(), responseUsersList.size());
        assertEquals(usersList.get(0).getName(), responseUsersList.get(0).getName());
        assertEquals(usersList.get(1).getEmail(), responseUsersList.get(1).getEmail());
    }
}
