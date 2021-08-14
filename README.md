# Space Agency Task

* [Task](#task)
* [Running](#running)
* [Initial accounts](#initial-accounts)
* [API](#api)
* [Testing](#testing)
* [Technologies](#technologies)

## Task
Implement a sample data hub application for space agencies. 
Space agencies store their satellites images (products) in large data hubs.

## Running
mvn spring-boot:run

Default server port 8080

## Initial accounts
Access to API is protected using HTTP Basic Authentication

Start-up accounts:
* Manager (login: "manager", password: "manager")
* Customer (login: "customer", password: "customer")

## API
API is divided for Manager and Customer roles.

### Classes
#### Mission
* String name (id)
* MissionType type (PANCHROMATIC, MULTISPECTRAL, HYPERSPECTRAL)
* LocalDateTime startDate
* LocalDateTime endDate

#### Product
* Long id
* Mission mission
* LocalDateTime acquisitionDate
* Double x1
* Double y1
* Double x2
* Double y2
* Integer price
* String url (hidden for customers that does not bought product)

#### Order
* Long id
* User customer
* Set<Product> products

#### User
* Long id
* String username
* String password
* UserRole role (MANAGER, CUSTOMER)

### Manager API
#### Missions
##### Add mission
* POST /api/admin/missions 
* Body - Mission object

##### Edit mission
* POST /api/admin/missions/{name}
* {name} - Mission name (id)
* Body - Mission edited object (name cannot be changed)

##### Delete mission
* DELETE /api/admin/missions/{name}

#### Products
##### Add product
* POST /api/admin/products
* Body - Product object

##### Delete product
* DELETE /api/admin/products/{id}

### Customer API
#### Products
##### Get products by mission name
* GET /api/mission/{name}/products
* {name} - Mission name

##### Get products by mission type
* GET /api/mission/type/{type}/products
* {type} - Mission type (PANCHROMATIC, MULTISPECTRAL, HYPERSPECTRAL)

##### Get products
* GET /api/products
* Query - startDate - start date from which to search products (optional)
* Query - endDate - end date to which to search products (optional)

##### Get products covering point
* GET /api/products/covering
* Query - latitude - latitude coordinate
* Query - longitutde - longitude coordinate

#### Orders
##### Get customer orders
* GET /api/orders

##### Make order
* POST /api/orders
* Body - List of Product ids

##### Get most ordered products (returns max 10 objects)
* GET /api/orders/most/products

##### Get most ordered missions (returns max 10 objects)
* GET /api/orders/most/missions

### Example requests
* curl -u manager:manager http://localhost:8080/api/admin/missions -H "Content-Type: application/json" -d '{ "name": "MissionX", "type": "PANCHROMATIC", "startDate": "2021-08-14T15:43:32.951", "endDate": "2021-08-14T15:43:32.951" }'
* curl -u customer:customer http://localhost:8080/api/products?startDate=2021-08-14T15:43:32.951

## Testing
mvn test

## Technologies
* Java Spring (JPA)
* H2 Database
* JUnit
* RestAssured
