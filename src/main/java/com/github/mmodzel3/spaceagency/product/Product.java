package com.github.mmodzel3.spaceagency.product;

import com.github.mmodzel3.spaceagency.mission.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public
class Product {
    @Id
    @GeneratedValue
    private Long id;

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
