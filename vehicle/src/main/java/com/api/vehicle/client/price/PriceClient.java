package com.api.vehicle.client.price;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PriceClient {
    private WebClient webClient;
    private ModelMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(PriceClient.class);
    public PriceClient(WebClient pricing, ModelMapper mapper) {
        this.webClient = pricing;
        this.mapper = mapper;
    }
    public String getPrice(Long id){
        try {
            Price price = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/services/price")
                            .queryParam("id",id)
                            .build()

                    )
                    .retrieve().bodyToMono(Price.class).block();

            return String.format("%s %s", price.getCurrency(), price.getPrice());
        }
        catch (Exception e){
            logger.error("Unexpected error retrieving price for vehicle {}", id, e);
        }
        return "(consult price)";
    }
}
