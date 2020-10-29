package com.api.vehicle.api;

import com.api.vehicle.domain.car.Car;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarResourceAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {

    public EntityModel<Car> toResource(Car car) {
        return new EntityModel<>(car,
                linkTo(methodOn(CarController.class).get(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).list()).withRel("cars"));

    }

    @Override
    public EntityModel<Car> toModel(Car entity) {
        return null;
    }
}
