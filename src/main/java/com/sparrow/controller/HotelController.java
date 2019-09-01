package com.sparrow.controller;

import com.sparrow.dto.*;
import com.sparrow.model.*;
import com.sparrow.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "api/hotels")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HotelController {

    public static final String ALEKSANDAR_VUJASINOVIC = "aleksandar.vujasinovic";
    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelReservationService hotelReservationService;

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
        return ResponseEntity.ok(hotelService.searchRoomsByCurrentDate(id));
    }

    @GetMapping(value = "/{id}/services")
    public ResponseEntity<Set<HotelServices>> getHotelServices(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.findById(id).getHotelServices());
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Hotel>> getSearchHotels(@RequestParam String place,
                                                       @RequestParam(required = false) Date start,
                                                       @RequestParam(required = false) Date end) {
        HotelSearchDto hotelSearchDto = new HotelSearchDto(place, start, end);

        return ResponseEntity.ok(hotelService.search(hotelSearchDto));
    }

    @PostMapping(value = "/reservation")
    public ResponseEntity postHotelReservation(@RequestBody HotelReservation hotelReservation) {
        return ResponseEntity.ok(hotelReservationService.save(hotelReservation));
    }

    @GetMapping(value = "/reservations/{id}")
    public ResponseEntity<List<HotelReservation>> getHotelReservations(@PathVariable Long id) {
        return ResponseEntity.ok(hotelReservationService.findAll());
    }

    @GetMapping(value = "/reservations-active/{id}")
    public ResponseEntity<List<HotelReservationDto>> getHotelReservationsActive(@PathVariable Long id) {
        User user = userService.findById(id);
        List<HotelReservation> hotelReservations = hotelReservationService.findActiveByUser(user);
        return ResponseEntity.ok(hotelReservationService.createReservationsDto(hotelReservations));
    }

    @GetMapping(value = "/reservations-finished/{id}")
    public ResponseEntity<List<HotelReservationDto>> getHotelReservationsFinished(@PathVariable Long id) {
        User user = userService.findById(id);
        List<HotelReservation> hotelReservations = hotelReservationService.findFinishedByUser(user);
        return ResponseEntity.ok(hotelReservationService.createReservationsDto(hotelReservations));
    }

    @GetMapping(value = "/{id}/rooms/search")
    public ResponseEntity<Set<PriceListItem>> getSearchHotelRoom(@RequestParam Date start,
                                                                 @RequestParam Date end,
                                                                 @RequestParam Integer guests,
                                                                 @RequestParam Integer rooms,
                                                                 @RequestParam Integer capacity,
                                                                 @PathVariable Long id) {
        RoomSearchDto roomSearchDto = new RoomSearchDto(start, end, guests, rooms, capacity);
        return ResponseEntity.ok(hotelService.searchRooms(roomSearchDto, id));
    }

    @ExceptionHandler
    public void onException(Exception e, HttpServletResponse response) {
        exceptionHandlerService.handle(e, response);
    }

}
