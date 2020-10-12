package com.services.pricing.service;

import com.services.pricing.data.Price;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class PriceService {

    /**
     * Mock cars with prices
     */
    private static final Map<Long, Price> PRICES = LongStream
            .range(1, 20)
            .mapToObj(i -> new Price(i, randomPrice(),"USD"))
            .collect(Collectors.toMap(Price::getId, p -> p));

    /**
     * @return price
     */
    private static BigDecimal randomPrice() {
        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal(5000d)).setScale(2, RoundingMode.HALF_UP);
    }

    public Price getPrice(Long id) throws PriceException {
        System.out.print(PRICES);
        if (!PRICES.containsKey(id)){
            throw new PriceException("The car with" + id +"does not exist");
        }
        return PRICES.get(id);
    }

}
