package com.imooc.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup (wac).build ();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform (MockMvcRequestBuilders.get ("/user")
                .contentType (MediaType.APPLICATION_JSON_UTF8)
                .param ("username", "jojo")
                .param ("age", "10")
                .param ("sex", "m")
                .param ("size", "15")
                .param ("page", "3")
                .param ("sort", "age,desc"))
                .andExpect (MockMvcResultMatchers.status ().isOk ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.length()").value (3))
                .andReturn ().getResponse ().getContentAsString ();
        log.info ("result_Success : {}", result);
    }

    @Test
    public void whenGenInfoSuccess() throws Exception {
        String result = mockMvc.perform (MockMvcRequestBuilders.get ("/user/1")
                .contentType (MediaType.APPLICATION_JSON_UTF8))
                .andExpect (MockMvcResultMatchers.status ().isOk ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.username").value ("tom"))
                .andReturn ().getResponse ().getContentAsString ();
        log.info ("result_Info : {}", result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform (MockMvcRequestBuilders.get ("/user/a")
                .contentType (MediaType.APPLICATION_JSON_UTF8))
                .andExpect (MockMvcResultMatchers.status ().is4xxClientError ());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date ();
        String content = "{\"id\":1,\"password\":null,\"username\":\"tom\",\"birthday\":\""+date.getTime ()+"\"}";
        log.info ("date : {}", date.getTime ());
        String result = mockMvc.perform (MockMvcRequestBuilders.post ("/user")
                .contentType (MediaType.APPLICATION_JSON_UTF8)
                .content (content))
                .andExpect (MockMvcResultMatchers.status ().isOk ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.id")
                        .value (1))
                .andReturn ().getResponse ().getContentAsString ();
        log.info ("create_result_success : {}",result);
    }

    @Test
    public void  whenUpdateSuccess()throws Exception {
        Date date = new Date (LocalDateTime.now ().plusYears (1).atZone (ZoneId.systemDefault ()).toInstant ().toEpochMilli ());
        String content = "{\"id\":1,\"password\":null,\"username\":\"tom\",\"birthday\":\""+date.getTime ()+"\"}";
        log.info ("date : {}", date.getTime ());
        String result = mockMvc.perform (MockMvcRequestBuilders.put ("/user/1")
                .contentType (MediaType.APPLICATION_JSON_UTF8)
                .content (content))
                .andExpect (MockMvcResultMatchers.status ().isOk ())
                .andExpect (MockMvcResultMatchers.jsonPath ("$.id").value ("1"))
                .andReturn ().getResponse ().getContentAsString ();
        log.info ("update_result_success : {}",result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform (MockMvcRequestBuilders.delete ("/user/1")
        .contentType (MediaType.APPLICATION_JSON_UTF8))
                .andExpect (MockMvcResultMatchers.status ().isOk ());
    }
}
