package com.github.mmodzel3.spaceagency.product;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.mmodzel3.spaceagency.order.OrderService;
import com.github.mmodzel3.spaceagency.user.User;
import com.github.mmodzel3.spaceagency.user.UserRole;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@JsonComponent
class ProductSerializer extends JsonSerializer<Product> {

    private final OrderService orderService;

    public ProductSerializer(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeObjectField("mission", product.getMission());
        jsonGenerator.writeObjectField("acquisitionDate", product.getAcquisitionDate());
        jsonGenerator.writeNumberField("x1", product.getX1());
        jsonGenerator.writeNumberField("y1", product.getY1());
        jsonGenerator.writeNumberField("x2", product.getX2());
        jsonGenerator.writeNumberField("y2", product.getY2());
        jsonGenerator.writeNumberField("price", product.getPrice());

        if (orderService.doesCustomerBoughtProduct(user, product) || user.getRole().equals(UserRole.MANAGER)) {
            jsonGenerator.writeStringField("url", product.getUrl());
        }

        jsonGenerator.writeEndObject();
    }
}
