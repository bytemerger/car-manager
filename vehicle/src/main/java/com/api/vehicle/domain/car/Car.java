package com.api.vehicle.domain.car;

import com.api.vehicle.domain.Location;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Location location = new Location();

    @Embedded
    private Detail detail = new Detail();

    @Transient
    private String price;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
