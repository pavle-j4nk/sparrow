package com.sparrow.controller;

import com.sparrow.model.Hotel;
import com.sparrow.service.AddressService;
import com.sparrow.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/sa")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AddressService addressService;

    //TODO: Add Rent-a-car and flight services

    @GetMapping(value = "/hotels")
    public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> hotels = hotelService.findAll();
        return ResponseEntity.ok(hotels);
    }

    @PostMapping(value = "/hotels")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        logger.info("Creating new hotel with following attributes: ");
        logger.info(String.format("Name: %s, \n Description: %s, \n Address: %s, \n", hotel.getName(), hotel.getDescription(), hotel.getAddress()));
        addressService.save(hotel.getAddress());
        Hotel savedHotel = hotelService.save(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    @PutMapping(value = "/hotel/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel h = hotelService.findById(id);
        if (h == null) {
            return (ResponseEntity<Hotel>) ResponseEntity.notFound();
        }

        logger.info(String.format("Updating hotel with %f id."));
        logger.info(String.format("Setting following attributes: \n name : %s \n description : %s \n address : %s",
                hotel.getName(), hotel.getDescription(), hotel.getAddress()));

        h.setName(hotel.getName());
        h.setAddress(hotel.getAddress());
        h.setDescription(hotel.getDescription());
        h.setId(hotel.getId());
        h.setRooms(hotel.getRooms());

        Hotel updatedHotel = hotelService.save(h);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping(value = "/hotels/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        if (hotel == null) {
            return (ResponseEntity<Hotel>) ResponseEntity.notFound();
        }

        logger.info(String.format("Deleting hotel %s", hotel.getName()));
        hotelService.delete(hotel);
        logger.info("Hotel deleted");

        return ResponseEntity.ok(hotel);
    }
}
