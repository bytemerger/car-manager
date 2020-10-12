package com.services.pricing.controller;

import com.services.pricing.data.Price;
import com.services.pricing.service.PriceException;
import com.services.pricing.service.PriceService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services/price")
public class MainController {
    @Autowired
    private PriceService priceService;
    @GetMapping
    public ResponseEntity<?> getPrice(@RequestParam Long id){
        try {
            return new ResponseEntity<Price>(priceService.getPrice(id), HttpStatus.OK);
        } catch (PriceException e) {
            return new ResponseEntity<String>("The price of car with id "+id+" does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
