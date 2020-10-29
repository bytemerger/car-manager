package com.api.vehicle.service;

import com.api.vehicle.client.location.LocationClient;
import com.api.vehicle.client.price.PriceClient;
import com.api.vehicle.domain.Location;
import com.api.vehicle.domain.car.Car;
import com.api.vehicle.domain.car.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository repository;
    private final PriceClient priceClient;
    private final LocationClient locationClient;

    public CarService(CarRepository repository, PriceClient priceClient, LocationClient locationClient) {
        this.repository = repository;
        this.priceClient = priceClient;
        this.locationClient = locationClient;
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return (List<Car>) repository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id){

        Optional<Car> car = repository.findById(id);
        car.orElseThrow(()-> new CarNotFoundException("This car does not with id "+id+" exist"));

        car.get().setPrice(priceClient.getPrice(id));
        car.get().setLocation(locationClient.getAddress(new Location()));

        return car.get();
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car){
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetail(car.getDetail());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id){
        Optional<Car> car = repository.findById(id);
        if(car.isPresent()) {
            repository.delete(car.get());
        } else {
            throw new CarNotFoundException("This car does not with id "+id+" exist");
        }

    }
}
