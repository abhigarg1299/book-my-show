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
}
