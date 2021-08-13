package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.Product;
import com.github.mmodzel3.spaceagency.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
