package com.khameneh.route.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class RouteControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;



    
    @Test
    //@WithMockUser(username = "user1", password = "1234", roles = "USER", setupBefore = TestExecutionEvent.TEST_METHOD)
	public void awards_inquery_for_movie_should_return_NA() throws  Exception {
		//Given 
		
        MvcResult result = mockMvc.perform(post("/api/route/getclusters")
        		.header("Authorization", "Basic azNlbkFudDE2Ol11bXB5U2lsdmVyNjM=")
        		.content("[{\"id\":\"1001\",\"city\":\"Amsterdam\",\"zip\":\"1012 RR\",\"lat\":\"52.372992\",\"lng\":\"4.890011\",\"cluster\":\"0\"},{\"id\":\"1002\",\"city\":\"Roden\",\"zip\":\"9301 KB\",\"lat\":\"53.1378\",\"lng\":\"6.435663\",\"cluster\":\"2\"},{\"id\":\"1003\",\"city\":\"Berkel En Rodenrijs\",\"zip\":\"2651 JN\",\"lat\":\"51.99419\",\"lng\":\"4.474514\",\"cluster\":\"0\"},{\"id\":\"1004\",\"city\":\"Tilburg\",\"zip\":\"5042 MC\",\"lat\":\"51.570384\",\"lng\":\"5.054236\",\"cluster\":\"1\"},{\"id\":\"1005\",\"city\":\"Breda\",\"zip\":\"4834 HE\",\"lat\":\"51.572232\",\"lng\":\"4.804898\",\"cluster\":\"0\"},{\"id\":\"1006\",\"city\":\"Amsterdam\",\"zip\":\"1098 LE\",\"lat\":\"52.349894\",\"lng\":\"4.939671\",\"cluster\":\"0\"},{\"id\":\"1007\",\"city\":\"Breda\",\"zip\":\"4826 HN\",\"lat\":\"51.607022\",\"lng\":\"4.78928\",\"cluster\":\"0\"},{\"id\":\"1008\",\"city\":\"Enschede\",\"zip\":\"7523 GG\",\"lat\":\"52.239233\",\"lng\":\"6.893108\",\"cluster\":\"2\"},{\"id\":\"1009\",\"city\":\"Amsterdam\",\"zip\":\"1018 GD\",\"lat\":\"52.362463\",\"lng\":\"4.913075\",\"cluster\":\"0\"},{\"id\":\"1011\",\"city\":\"Amstelveen\",\"zip\":\"1181 ZL\",\"lat\":\"52.303557\",\"lng\":\"4.859719\",\"cluster\":\"0\"},{\"id\":\"1014\",\"city\":\"Ijmuiden\",\"zip\":\"1971 EP\",\"lat\":\"52.461168\",\"lng\":\"4.622734\",\"cluster\":\"0\"},{\"id\":\"1015\",\"city\":\"Nijmegen\",\"zip\":\"6542 PP\",\"lat\":\"51.841008\",\"lng\":\"5.839595\",\"cluster\":\"1\"},{\"id\":\"1016\",\"city\":\"Arnhem\",\"zip\":\"6822 GG\",\"lat\":\"51.988965\",\"lng\":\"5.921694\",\"cluster\":\"1\"},{\"id\":\"1018\",\"city\":\"Roermond\",\"zip\":\"6042 EJ\",\"lat\":\"51.208839\",\"lng\":\"6.020873\",\"cluster\":\"1\"},{\"id\":\"1019\",\"city\":\"Geleen\",\"zip\":\"6161 BP\",\"lat\":\"50.967117\",\"lng\":\"5.826826\",\"cluster\":\"1\"}]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

     // when, then
     assertTrue(result.getResponse().getContentAsString().contains("cluster"));
        
	}    

   
}
