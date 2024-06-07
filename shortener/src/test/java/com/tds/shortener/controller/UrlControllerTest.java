package com.tds.shortener.controller;

import com.tds.shortener.model.url;
import com.tds.shortener.repository.UrlRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
/**
 * teste de integração
***/
 public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    @Order(1)
    public void createUrlTest() throws Exception {

        String json = "{\"url\": \"https://www.youtube.com/watch?v=BxAdb8K2KDM\"}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/urls")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

        System.out.println("------------------------------------------------");
        System.out.println("createUrlTest:" + responseContent);
    }

    @Test
    @Order(2)
    public void listUrlsTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/urls/all")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("------------------------------------------------");
        System.out.println("ListUrlsTest:" + responseContent);
        System.out.println("------------------------------------------------");

    }

    @Test
    @Order(3)
    public void getAccessStatsTest() throws Exception  {

        List<url> optionalUrl = urlRepository.findAll();

        String firstShortUrl = optionalUrl.stream()
        .map(url::getShortUrl)
        .findFirst()
        .orElse(null);

        MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get("/api/urls/getstats/")
        .param("url", firstShortUrl)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();


        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("------------------------------------------------");
        System.out.println("getAccessStatsTest:" + responseContent);
        System.out.println("------------------------------------------------");

    }

    @Test
    @Order(4)
    public void listBynameTest() throws Exception{

        List<url> optionalUrl = urlRepository.findAll();

        String firstShortUrl = optionalUrl.stream()
        .map(url::getShortUrl)
        .findFirst()
        .orElse(null);

        MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get("/api/urls/byname/")
        .param("url", firstShortUrl)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("------------------------------------------------");
        System.out.println("listBynameTest:" + responseContent);
        System.out.println("------------------------------------------------");

    }

    @Test
    @Order(5)
    public void redirectTest() throws Exception{

        List<url> optionalUrl = urlRepository.findAll();

        String firstShortUrl = optionalUrl.stream()
        .map(url::getShortUrl)
        .findFirst()
        .orElse(null);

        MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get("/api/urls/redirect")
        .param("url", firstShortUrl)).andExpect(status().isFound()).andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("------------------------------------------------");
        System.out.println("redirectTest:" + responseContent);
        System.out.println("------------------------------------------------");
    }
}
