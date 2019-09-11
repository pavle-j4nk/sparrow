package com.sparrow.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparrow.backend.model.Airline;
import com.sparrow.backend.service.impl.AirlineServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AirlineControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Mock
    private AirlineServiceImpl airlineService;

    @MockBean
    private AirlineController airlineController;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void testGetAll() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(get("/api/airline")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testCreate() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(put("/api/airline", mapToJson(airline))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertNotEquals(500, status);

    }

    @Test
    public void testUpdate() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(post("/api/airline", mapToJson(airline))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertNotEquals(500, status);
    }

    @Test
    public void testGetFlights() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(get("/api/airline/1/flights")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertNotEquals(500, status);
    }

    @Test
    public void testGetFlight() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(get("/api/airline/flight/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertNotEquals(500, status);
    }

    @Test
    public void saveFlight() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(post("/api/airline/1/flight", mapToJson(airline))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertNotEquals(500, status);
    }

    @Test
    public void getAdministratedAirlineTest() throws Exception {
        Airline airline = new Airline();
        airline.setName("A1");

        Mockito.when(airlineService.getAll()).thenReturn(Arrays.asList(airline));
        Mockito.when(airlineService.save(airline)).thenReturn(airline);

        MvcResult result = mockMvc.perform(get("/api/airline/administrated")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertNotEquals(500, status);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


}
