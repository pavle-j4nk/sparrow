package com.sparrow.controller;

import com.sparrow.dto.HotelDto;
import com.sparrow.dto.HotelSearchDto;
import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.*;
import com.sparrow.service.ExceptionHandlerService;
import com.sparrow.service.HotelService;
import com.sparrow.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "api/hotels")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HotelController {

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotels() {
        return ResponseEntity.ok(hotelService.findAllExtended());
    }

    @PostMapping
    public ResponseEntity<Hotel> postHotel(@RequestBody NewHotelDto newHotelDto) {
        Hotel hotel = hotelService.create(newHotelDto);
        logger.info(String.format("Creating new hotel...\nName: %s\nDescription: %s\nAdministrator email: %s", hotel.getName(), hotel.getDescription(), hotel.getAdmin().getEmail()));
        return ResponseEntity.ok(hotelService.save(hotel));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> getHotelDetails(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        logger.info("Getting hotel details...");
        return ResponseEntity.ok(hotel);
    }

    @GetMapping(value = "/room/{roomId}")
    public ResponseEntity<Room> getRoomDetails(@PathVariable Long roomId) {
        Room room = roomService.findById(roomId);
        return ResponseEntity.ok(room);
    }

    @GetMapping(value = "/{id}/pricelist")
    public ResponseEntity<Set<PriceListItem>> getHotelPriceList(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        Set<PriceList> priceLists = hotel.getPriceLists();
        return ResponseEntity.ok(priceLists.stream().findFirst().get().getItems());
    }

    @GetMapping(value = "/{id}/services")
    public ResponseEntity<Set<HotelServices>> getHotelServices(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel.getHotelServices());
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Hotel>> getSearchHotels(@RequestParam String place,
                                                       @RequestParam(required = false) Date start,
                                                       @RequestParam(required = false) Date end) {
        HotelSearchDto hotelSearchDto = new HotelSearchDto(place, start, end);

        return ResponseEntity.ok(hotelService.search(hotelSearchDto));
    }

    @ExceptionHandler
    public void onException(Exception e, HttpServletResponse response) {
        exceptionHandlerService.handle(e, response);
    }

}
