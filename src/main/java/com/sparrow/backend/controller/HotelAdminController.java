package com.sparrow.backend.controller;

import com.sparrow.backend.model.*;
import com.sparrow.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api/ha")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HotelAdminController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private HotelReservationService hotelReservationService;

    @Autowired
    private HotelRoomDiscountService hotelRoomDiscountService;


    @PutMapping(value = "/hotels")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        addressService.save(hotel.getAddress());
        Hotel savedHotel = hotelService.update(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    @PutMapping(value = "/hotels/{hotelId}/room/{roomId}")
    public ResponseEntity<Hotel> updateHotelRoom(@RequestBody Room room, @PathVariable Long hotelId, @PathVariable Long roomId) {
        List<HotelReservation> hotelReservations = hotelReservationService.findActive();
        for (HotelReservation hr : hotelReservations) {
            for (Room r : hr.getRooms())
                if (r.getId() == room.getId()) {
                    return new ResponseEntity<Hotel>(hotelService.findById(hotelId), HttpStatus.FORBIDDEN);
                }
        }
        Hotel hotel = hotelService.updateRoom(hotelId, roomId, room);
        return ResponseEntity.ok(hotel);
    }


    @PostMapping(value = "hotels/{hotelId}/pricelistitem")
    public ResponseEntity<PriceListItem> postPriceListItem(@RequestBody PriceListItem priceListItem, @PathVariable Long hotelId) {
        PriceListItem pli = priceListItemService.create(priceListItem, hotelId);
        return ResponseEntity.ok(pli);
    }

    @DeleteMapping(value = "hotels/pricelistitem/{id}")
    public ResponseEntity<PriceListItem> deletePriceListItem(@PathVariable Long id) {
        PriceListItem priceListItem = priceListItemService.findById(id);

        List<HotelReservation> hotelReservations = hotelReservationService.findActive();
        for (HotelReservation hr : hotelReservations) {
            for (Room r : hr.getRooms())
                if (r.getId() == priceListItem.getRoom().getId()) {
                    return new ResponseEntity<>(priceListItem, HttpStatus.FORBIDDEN);
                }
        }
        if (priceListItem == null) {
            return (ResponseEntity<PriceListItem>) ResponseEntity.notFound();
        }
        priceListItemService.delete(priceListItem);
        return ResponseEntity.ok(priceListItem);
    }

    @GetMapping(value = "hotels/{id}/discount")
    public ResponseEntity<List<HotelRoomDiscount>> getHotelRoomDiscounts(@PathVariable Long id, @RequestParam Date tripStart, @RequestParam Date tripEnd) {
        List<HotelRoomDiscount> hotelRoomDiscounts = hotelRoomDiscountService.findAllActive(tripStart, tripEnd);
        hotelRoomDiscounts.removeIf(h -> h.getPriceListItem().getRoom().getHotel().getId() != id);
        return ResponseEntity.ok(hotelRoomDiscounts);
    }


    @PostMapping(value = "hotels/discount")
    public ResponseEntity<HotelRoomDiscount> postHotelRoomDiscount(@RequestBody HotelRoomDiscount hotelRoomDiscount) {
        return ResponseEntity.ok(hotelRoomDiscountService.save(hotelRoomDiscount));
    }
}
