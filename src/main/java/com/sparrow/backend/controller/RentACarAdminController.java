package com.sparrow.backend.controller;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.PriceListItem;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.service.AddressService;
import com.sparrow.backend.service.CarService;
import com.sparrow.backend.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/ra")
@CrossOrigin(origins = "*" , maxAge = 3600)
public class RentACarAdminController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private CarService carService;

    @PutMapping(value = "/rentacars/{rentacarId}")
    public ResponseEntity updateRentacar(@RequestBody RentACar rentACar , @PathVariable Long rentacarId) {
        addressService.save(rentACar.getAddress());
        RentACar rentACar1 = rentACarService.update(rentACar , rentacarId);
        return ResponseEntity.ok(rentACar1);
    }

    @PostMapping(value = "/rentacars/{rentacarId}/newCar")
    public ResponseEntity<Car> postPriceListItem(@RequestBody Car car, @PathVariable Long rentacarId) {
        Car car1 = carService.createCar(car, rentacarId);
        return ResponseEntity.ok(car1);

    }

    @DeleteMapping(value = "/rentacars/car/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        Car car = carService.findById(id);

        if (car == null) {
            return (ResponseEntity<Car>) ResponseEntity.notFound();
        }
        carService.delete(car);
        return ResponseEntity.ok(car);
    }

    @PutMapping(value = "/rentacars/{rentacarId}/car/{carId}")
    public ResponseEntity updateCar(@RequestBody Car car, @PathVariable Long rentacarId, @PathVariable Long carId) {
       Car carReturn = carService.updateCar(car , carId);

       return ResponseEntity.ok(carReturn);
    }
}
