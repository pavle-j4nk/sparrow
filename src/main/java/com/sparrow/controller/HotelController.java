package com.sparrow.controller;

import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.Hotel;
import com.sparrow.model.PriceList;
import com.sparrow.model.PriceListItem;
import com.sparrow.model.Room;
import com.sparrow.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "api/hotels")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HotelController {

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    HotelService hotelService;

    @Autowired
    UserService userService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        return ResponseEntity.ok(hotelService.findAll());
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

    @GetMapping(value = "/{id}/pricelist")
    public ResponseEntity<Set<PriceListItem>> getHotelPriceList(@PathVariable Long id) {
        PriceList priceList = priceListService.findByHotelId(id);
        Set<PriceListItem> priceListItems = priceListItemService.findByPriceList(priceList);
        return ResponseEntity.ok(priceListItems);
    }

    @GetMapping(value = "/edit/{id}")
    public String getHotelEdit(@PathVariable Long id) {
        logger.info("Passed id :" + id);
        return null;
    }

    @GetMapping(value = "/delete/{id}")
    public String getHotelDelete(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        hotelService.delete(hotel);
        return null;
    }

    @PostMapping(value = "/room")
    public String postRoom() {

        return null;
    }

    @ExceptionHandler
    public void onException(Exception e, HttpServletResponse response) {
        exceptionHandlerService.handle(e, response);
    }

}
