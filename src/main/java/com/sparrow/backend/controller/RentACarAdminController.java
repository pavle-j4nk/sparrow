package com.sparrow.backend.controller;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarSale;
import com.sparrow.backend.model.PriceListItem;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.service.AddressService;
import com.sparrow.backend.service.CarSaleService;
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

    @Autowired
    private CarSaleService carSaleService;

    @PutMapping(value = "/rentacars/{rentacarId}")
    public ResponseEntity updateRentacar(@RequestBody RentACar rentACar , @PathVariable Long rentacarId) {
        addressService.save(rentACar.getAddress());
        RentACar rentACar1 = rentACarService.update(rentACar , rentacarId);
        return ResponseEntity.ok(rentACar1);
    }

    @PostMapping(value = "/rentacars/{rentacarId}/newCar")
    public ResponseEntity<Car> postNewCar(@RequestBody Car car, @PathVariable Long rentacarId) {
        Car car1 = carService.createCar(car, rentacarId);
        return ResponseEntity.ok(car1);

    }

    @PostMapping(value = "rentacars/{rentacarId}/{carId}/newCarSale")
    public ResponseEntity postNewCarSale(@RequestBody CarSale carSale, @PathVariable Long rentacarId ,
                                            @PathVariable Long carId){
        Car car = carService.findById(carId);
        return ResponseEntity.ok(carSaleService.createCarSale(carSale , car));
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
