package com.api.vehicle.client.location;

import com.api.vehicle.domain.Location;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class LocationClient {
    private static final Logger logger = LoggerFactory.getLogger(LocationClient.class);

    private final WebClient client;
    private final ModelMapper mapper;

    public LocationClient(WebClient location,
                      ModelMapper mapper) {
        this.client = location;
        this.mapper = mapper;
    }
    public Location getAddress(Location location) {
        try {
            Location address = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/cars/1/location")
                            /*.queryParam("lat", location.getLat())
                            .queryParam("lon", location.getLon())*/
                            .build()
                    )
                    .retrieve().bodyToMono(Location.class).block();

            mapper.map(Objects.requireNonNull(address), location);

            return location;
        } catch (Exception e) {
            logger.warn("Map service is down");
            return location;
        }
    }
}
