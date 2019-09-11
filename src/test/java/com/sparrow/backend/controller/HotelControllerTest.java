package com.sparrow.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparrow.backend.dto.NewHotelDto;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.model.PriceList;
import com.sparrow.backend.model.Room;
import com.sparrow.backend.repository.HotelRepository;
import com.sparrow.backend.service.HotelReservationService;
import com.sparrow.backend.service.impl.HotelServiceImpl;
import com.sparrow.backend.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {

    public static final String PLACE = "place";
    public static final String MAIL_MAIL_COM = "mail@mail.com";
    public static final String DESCRIPTION = "Description";
    public static final String NAME = "Name";
    public static final String NAME1 = "Name";
    @Autowired
    protected MockMvc mockMvc;

    @Mock
    private HotelServiceImpl hotelService;

    @Mock
    private RoomServiceImpl roomService;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelReservationService hotelReservationService;

    @MockBean
    private HotelController hotelController;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void testAllHotels() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setName("hotel");

        when(hotelService.save(hotel)).thenReturn(hotel);
        when(hotelService.findAll()).thenReturn(Arrays.asList(hotel));

        MvcResult result = mockMvc.perform(get("/api/hotels")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testPostHotel() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName(NAME);

        NewHotelDto dto = new NewHotelDto();
        dto.setName(NAME);
        dto.setDescription(DESCRIPTION);
        dto.setUserEmail(MAIL_MAIL_COM);

        when(hotelService.save(hotel)).thenReturn(hotel);
        when(hotelService.create(dto)).thenReturn(hotel);

        MvcResult result = mockMvc.perform(post("/api/hotels", mapToJson(dto)).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertNotNull(result);
    }

    @Test
    public void testOneHotel() throws Exception {
        mockMvc.perform(get("/api/hotels/" + 1L)).andExpect(status().isOk());
    }

    @Test
    public void testRoom() throws Exception {
        Room room = new Room();
        room.setId(1L);
        room.setName("room");

        when(roomService.save(room));

        MvcResult result = mockMvc.perform(get("/api/hotels/room/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testHotelPriceList() throws Exception {
        PriceList priceList = new PriceList();
        priceList.setId(1L);

        MvcResult result = mockMvc.perform(get("/api/hotels/{id}}/pricelist", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testHotelPriceListDates() throws Exception {
        PriceList priceList = new PriceList();
        priceList.setId(1L);

        MvcResult result = mockMvc.perform(get("/api/hotels/{id}}/pricelist/dates", 1L,
                new Date(Calendar.getInstance().getTime().getTime()),
                new Date(Calendar.getInstance().getTime().getTime()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testHotelServices() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/hotels/{id}}/services", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testSearch() throws Exception {
        Hotel h = new Hotel();
        h.setName("Name");
        h.setId(1L);

        MvcResult result = mockMvc.perform(get("/api/hotels/search", PLACE,
                new Date(Calendar.getInstance().getTime().getTime()),
                        new Date(Calendar.getInstance().getTime().getTime()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testPostReservation() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName(NAME);

        HotelReservation hr = new HotelReservation();
        hr.setId(1L);
        hr.setPrice(200.0);

        when(hotelService.save(hotel)).thenReturn(hotel);

        when(hotelReservationService.save(hr)).thenReturn(hr);

        MvcResult result = mockMvc.perform(post("/api/hotels/reservation", mapToJson(hr)).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testDeleteReservation() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName(NAME);

        HotelReservation hr = new HotelReservation();
        hr.setId(1L);
        hr.setPrice(200.0);

        when(hotelService.save(hotel)).thenReturn(hotel);

        MvcResult result = mockMvc.perform(delete("/api/hotels/reservation/{id}", 1L).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testSearchRooms() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/hotels/{id}/rooms/search",
                new Date(Calendar.getInstance().getTime().getTime()),
                new Date(Calendar.getInstance().getTime().getTime()),
                    1,
                    1,
                    1,
                    1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
