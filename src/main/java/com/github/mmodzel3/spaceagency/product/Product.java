package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.Mission;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
class Product {
    @Id
    @GeneratedValue
    private String id;

    @ManyToOne
    private Mission mission;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime acquisitionDate;

    private Double x1;
    private Double y1;
    private Double x2;
    private Double y2;

    private Integer price;

    private String url;
}
