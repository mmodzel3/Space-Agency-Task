package com.github.mmodzel3.spaceagency.user;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class UserManagerRequestBuilder {
    public static RequestSpecification given() {
        return RestAssured.given()
                .auth()
                .basic("manager", "manager")
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json");
    }
}
