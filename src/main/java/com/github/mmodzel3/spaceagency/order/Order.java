package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.product.Product;
import com.github.mmodzel3.spaceagency.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_products")
    private Set<Product> products;
}
