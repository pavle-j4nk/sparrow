package com.sparrow.backend.controller;

import com.sparrow.backend.dto.CarReservationDto;
import com.sparrow.backend.dto.CarSearchDto;
import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.dto.RentACarSearchDto;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.model.User;
import com.sparrow.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "api/rentacars")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RentACarController {

    private Logger logger = LoggerFactory.getLogger(RentACarController.class);

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Autowired
    private UserService userService;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarReservationService carReservationService;

    @GetMapping
    public ResponseEntity<List<RentACarDto>> getRentACar() {
        return ResponseEntity.ok(rentACarService.findAllExtended());
    }

   @GetMapping(value = "/{id}")
    public ResponseEntity<RentACar> getRentacarDetails(@PathVariable Long id) {
        RentACar rentACar = rentACarService.findById(id);
        logger.info("Getting hotel details...");
        return ResponseEntity.ok(rentACar);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<RentACar>> getSearchRentacars(@RequestParam String city) {
        RentACarSearchDto rentACarSearchDto = new RentACarSearchDto(city);

        return ResponseEntity.ok(rentACarService.search(rentACarSearchDto));
    }

    @GetMapping(value = "/{id}/cars/search")
    public ResponseEntity<Set<Car>> getSearchHotelRoom(@RequestParam Date start,
                                                       @RequestParam Date end,
                                                       @RequestParam Integer seats,
                                                       @RequestParam Long priceLow,
                                                       @PathVariable Long id) {
        CarSearchDto carSearchDto = new CarSearchDto(start, end,seats , priceLow);
        return ResponseEntity.ok(rentACarService.searchCars(carSearchDto, id));
    }

    @PostMapping(value = "/reservation")
    public ResponseEntity postCarReservation(@RequestBody CarReservation carReservation) {
        return ResponseEntity.ok(carReservationService.save(carReservation));
    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id){
        Car car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping(value = "/reservations-active/{id}")
    public ResponseEntity<List<CarReservationDto>> getHotelReservationsActive(@PathVariable Long id) {
        User user = userService.findById(id);
        List<CarReservation> carReservations = carReservationService.findActiveByUser(user);
        return ResponseEntity.ok(carReservationService.createReservationsDto(carReservations));
    }

    @GetMapping(value = "/reservations-finished/{id}")
    public ResponseEntity<List<CarReservationDto>> getHotelReservationsFinished(@PathVariable Long id) {
        User user = userService.findById(id);
        List<CarReservation> hotelReservations = carReservationService.findFinishedByUser(user);
        return ResponseEntity.ok(carReservationService.createReservationsDto(hotelReservations));
    }

    @DeleteMapping(value = "/reservation/{id}")
    public ResponseEntity<CarReservation> deleteCarReservation(@PathVariable Long id) {
        carReservationService.delete(id);
        return ResponseEntity.ok(new CarReservation());
    }

}
