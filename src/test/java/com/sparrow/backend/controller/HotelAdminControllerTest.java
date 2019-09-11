package com.sparrow.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.PriceListItem;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotelAdminControllerTest {
    public static final String NAME = "NAME";
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
    public void testUpdateHotel() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName(NAME);

        when(hotelService.save(hotel)).thenReturn(hotel);

        MvcResult result = mockMvc.perform(put("/api/ha/hotels", hotel).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void testUpdatePriceListITem() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName(NAME);

        Room room = new Room();
        room.setId(1L);
        room.setName("Name");

        PriceListItem priceListItem = new PriceListItem();
        priceListItem.setId(1L);

        when(hotelService.save(hotel)).thenReturn(hotel);

        MvcResult result = mockMvc.perform(put("/api/ha/hotels/{hotelId}/room/{roomId}",1L, 1L).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertNotEquals(500, result.getResponse().getStatus());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
