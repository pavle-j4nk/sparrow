package com.sparrow.controller;

import com.sparrow.model.Hotel;
import com.sparrow.model.PriceListItem;
import com.sparrow.model.Room;
import com.sparrow.service.AddressService;
import com.sparrow.service.HotelService;
import com.sparrow.service.PriceListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping(value = "/hotels")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        addressService.save(hotel.getAddress());
        Hotel savedHotel = hotelService.update(hotel);
        return ResponseEntity.ok(savedHotel);
    }

    @PutMapping(value = "/hotels/{hotelId}/room/{roomId}")
    public ResponseEntity<Hotel> updateHotelRoom(@RequestBody Room room, @PathVariable Long hotelId, @PathVariable Long roomId) {
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
        if (priceListItem == null) {
            return (ResponseEntity<PriceListItem>) ResponseEntity.notFound();
        }
        priceListItemService.delete(priceListItem);
        return ResponseEntity.ok(priceListItem);
    }
}
