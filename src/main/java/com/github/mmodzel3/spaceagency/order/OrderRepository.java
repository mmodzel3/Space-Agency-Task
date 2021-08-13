package com.github.mmodzel3.spaceagency.order;

import com.github.mmodzel3.spaceagency.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(User user);
}
