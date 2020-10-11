package com.car.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LocationController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/car/{id}/location")
    public ResponseEntity<?> getCarLocation(@PathVariable("id")Long id){
        LocationModel location = restTemplate.getForObject("http://my-json-server.typicode.com/proalgor/mock-api/location",LocationModel.class);
        return new ResponseEntity<LocationModel>(location, HttpStatus.OK);
    }
}
