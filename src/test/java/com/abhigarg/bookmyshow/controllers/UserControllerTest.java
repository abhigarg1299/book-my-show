package com.abhigarg.bookmyshow.controllers;

import com.abhigarg.bookmyshow.entities.User;
import com.abhigarg.bookmyshow.exceptions.UnauthorizedException;
import com.abhigarg.bookmyshow.pojo.LoginRequest;
import com.abhigarg.bookmyshow.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestLoginFailedWhenPasswordIsMissing() throws Exception {
        String loginRequest = "{ \"emailId\": \"testuser@gmail.com\" }";
        String expectedResponse = "{\"success\":false,\"message\":\"{password=Password is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestLoginFailedWhenEmailIsMissing() throws Exception {
        String loginRequest = "{\"password\":\"test-password\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{emailId=Email Id is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestLoginFailedWhenEmailIsInvalid() throws Exception {
        String loginRequest = "{\"emailId\":\"test-user\",\"password\":\"test-password\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{emailId=Email is not valid}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestLoginSuccessfulWhenUserIsAuthorised() throws Exception {
        LoginRequest loginRequest = new LoginRequest("abhi666g@gmail.com", "QWERTY");
        String expectedResponse = "{\"success\":false,\"message\":\"User is not authorized\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"emailId\":\"abhi666g@gmail.com\",\"password\":\"QWERTY\"}")
                .contentType(MediaType.APPLICATION_JSON);


        Mockito.when(userService.Login(loginRequest)).thenThrow(new UnauthorizedException("User is not authorized"));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }


    @Test
    public void TestLoginSuccessful() throws Exception {
        LoginRequest loginRequest = new LoginRequest("abhi666g@gmail.com", "QWERTY");
        String expectedResponse = "{\"id\":7,\"firstName\":\"Abhi\",\"lastName\":null,\"emailId\":\"abhi666g@gmail.com\",\"password\":\"abhigarg@123\",\"createdAt\":\"2022-08-27T06:17:20.000+00:00\",\"updatedAt\":\"2022-08-27T06:17:20.000+00:00\"}";
        User expectedUser = objectMapper.readValue(expectedResponse, User.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"emailId\":\"abhi666g@gmail.com\",\"password\":\"QWERTY\"}")
                .contentType(MediaType.APPLICATION_JSON);


        Mockito.when(userService.Login(loginRequest)).thenReturn(expectedUser);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserSuccessfully() throws Exception {
        String createUserRequest = "{\"firstName\":\"Abhi\",\"lastName\":null,\"emailId\":\"abhi6666g@gmail.com\",\"password\":\"abhigarg@123\"}";
        User createUserObject = objectMapper.readValue(createUserRequest, User.class);

        String expectedResponse = "{\"id\":21,\"firstName\":\"Abhi\",\"lastName\":null,\"emailId\":\"abhi6666g@gmail.com\",\"password\":\"abhigarg@123\",\"createdAt\":\"2022-09-10T07:50:53.809+00:00\",\"updatedAt\":\"2022-09-10T07:50:53.809+00:00\"}";
        User expectedUser = objectMapper.readValue(expectedResponse, User.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(createUserRequest)
                .contentType(MediaType.APPLICATION_JSON);


        Mockito.when(userService.Add(createUserObject)).thenReturn(expectedUser);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenFirstNameIsEmpty() throws Exception {
        String userRequest = "{\"emailId\":\"abhi6666g@gmail.com\",\"password\":\"abhigarg@123\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{firstName=Name is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenEmailIsEmpty() throws Exception {
        String userRequest = "{\"firstName\":\"abhi\",\"password\":\"abhigarg@123\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{emailId=Email Id is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenPasswordIsEmpty() throws Exception {
        String userRequest = "{\"firstName\":\"abhi\",\"emailId\":\"abhigarg@gmail.com\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{password=Password is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenPasswordMinimumLength() throws Exception {
        String userRequest = "{\"firstName\":\"abhi\",\"emailId\":\"abhigarg@gmail.com\",\"password\":\"1234\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{password=Password should have min 6 characters}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenPasswordAndEmailIsEmpty() throws Exception {
        String userRequest = "{\"firstName\":\"abhi\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{password=Password is mandatory, emailId=Email Id is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenNamePasswordAndEmailIsEmpty() throws Exception {
        String userRequest = "{}";
        String expectedResponse = "{\"success\":false,\"message\":\"{firstName=Name is mandatory, password=Password is mandatory, emailId=Email Id is mandatory}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void TestCreateUserFailedWhenEmailIsInValid() throws Exception {
        String userRequest = "{\"firstName\":\"abhi\",\"emailId\":\"abhigarggmail.com\",\"password\":\"123456\"}";
        String expectedResponse = "{\"success\":false,\"message\":\"{emailId=Email is not valid}\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }
}
