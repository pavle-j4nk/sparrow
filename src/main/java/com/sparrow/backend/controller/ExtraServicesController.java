package com.sparrow.backend.controller;

import com.sparrow.backend.model.ExtraService;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.HotelServices;
import com.sparrow.backend.service.ExtraServicesService;
import com.sparrow.backend.service.HotelService;
import com.sparrow.backend.service.HotelServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/extra-services")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExtraServicesController {

    @Autowired
    private ExtraServicesService extraServicesService;

    @Autowired
    private HotelServicesService hotelServicesService;

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<ExtraService>> getExtraServices() {
        return ResponseEntity.ok(extraServicesService.findAll());
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<HotelServices> postHotelService(@PathVariable Long id, @RequestBody HotelServices hotSer) {
        hotelServicesService.save(id, hotSer);
        return ResponseEntity.ok(hotSer);
    }

    @DeleteMapping(value = "/{id}/hotel/{hotelId}")
    public ResponseEntity<Hotel> deleteHotelService(@PathVariable Long id, @PathVariable Long hotelId) {
        Hotel hotel = hotelServicesService.delete(hotelId, id);
        return ResponseEntity.ok(hotel);
    }

}
