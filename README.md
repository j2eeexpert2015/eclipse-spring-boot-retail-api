# Eclipse Spring Boot Retail API

A small **Retail Product and Inventory API** designed for Eclipse, Spring Tools / STS, Docker, JUnit, Actuator, and future Spring Boot migration demos.

## Baseline

- Java 21
- Spring Boot 3.5.16
- Maven
- Spring Web
- Spring Data JPA
- Validation
- Actuator
- H2 for default local run
- PostgreSQL for Docker Compose
- JUnit 5 / Mockito / MockMvc
- Optional Testcontainers test

## Project Structure

```text
org.eclipsefeaturesdemo.retail
│
├── RetailApiApplication.java
├── controller
│   ├── ProductController.java
│   └── InventoryController.java
├── service
│   ├── ProductService.java
│   └── InventoryService.java
├── repository
│   ├── ProductRepository.java
│   └── StockAdjustmentRepository.java
├── model
│   ├── Product.java
│   ├── StockAdjustment.java
│   └── ProductCategory.java
├── dto
│   ├── ProductRequest.java
│   ├── ProductResponse.java
│   ├── StockAdjustmentRequest.java
│   └── StockAdjustmentResponse.java
├── config
│   └── RetailProperties.java
├── exception
│   ├── ProductNotFoundException.java
│   └── GlobalExceptionHandler.java
└── data
    └── DataInitializer.java
```

## Design Notes

This project intentionally keeps the domain flat and demo-friendly.

- `Product` is the main retail item.
- `StockAdjustment` stores `productId` as a simple `Long`.
- There are no JPA relationships.
- There is no cart, order, payment, customer, login, or security.
- Stock mutation belongs to `InventoryController` / `InventoryService`, not `ProductController`.

Dependency flow:

```text
ProductController
→ ProductService
→ ProductRepository
```

```text
InventoryController
→ InventoryService
→ ProductRepository
→ StockAdjustmentRepository
```

This gives a clean structure for Spring Tools navigation demos.

## Prerequisites

Install:

```text
Java 21
Maven 3.9+
Docker Desktop, optional for Docker lessons
```

Check versions:

```bash
java -version
mvn -version
docker version
```

## Build

```bash
mvn clean package
```

## Run Locally with H2

```bash
mvn spring-boot:run
```

Or run the packaged jar:

```bash
java -jar target/eclipse-spring-boot-retail-api-0.0.1-SNAPSHOT.jar
```

The app starts on:

```text
http://localhost:8080
```

## Test the API

### Health

```bash
curl http://localhost:8080/actuator/health
```

### Get all products

```bash
curl http://localhost:8080/api/products
```

Browser URL:

```text
http://localhost:8080/api/products
```

### Get one product

```bash
curl http://localhost:8080/api/products/1
```

Browser URL:

```text
http://localhost:8080/api/products/1
```

### Get low-stock products

```bash
curl http://localhost:8080/api/products/low-stock
```

Browser URL:

```text
http://localhost:8080/api/products/low-stock
```

### Filter products by category

```bash
curl "http://localhost:8080/api/products?category=ELECTRONICS"
```

Browser URL:

```text
http://localhost:8080/api/products?category=ELECTRONICS
```

### Create a product

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "USB-C Hub",
    "category": "ELECTRONICS",
    "price": 29.99,
    "availableQuantity": 15,
    "active": true
  }'
```

### Create a stock adjustment

Use a positive quantity to increase stock:

```bash
curl -X POST http://localhost:8080/api/inventory/adjustments \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": 5,
    "reason": "New shipment received"
  }'
```

Use a negative quantity to reduce stock:

```bash
curl -X POST http://localhost:8080/api/inventory/adjustments \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": -2,
    "reason": "Damaged stock removed"
  }'
```

### Get stock adjustments

```bash
curl http://localhost:8080/api/inventory/adjustments
```

Browser URL:

```text
http://localhost:8080/api/inventory/adjustments
```

### Get stock adjustments for one product

```bash
curl "http://localhost:8080/api/inventory/adjustments?productId=1"
```

Browser URL:

```text
http://localhost:8080/api/inventory/adjustments?productId=1
```

## Actuator URLs for Spring Tools / Runtime Lessons

These are exposed in `application.properties`:

```properties
management.endpoints.web.exposure.include=health,info,mappings,beans
```

Useful URLs:

```text
http://localhost:8080/actuator/health
http://localhost:8080/actuator/mappings
http://localhost:8080/actuator/beans
```

## H2 Console

H2 console is enabled for local demo use.

URL:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:retaildb
User Name: sa
Password: <empty>
```

## Run Tests

Run all default tests:

```bash
mvn test
```

Run one test class:

```bash
mvn -Dtest=ProductServiceTest test
```

```bash
mvn -Dtest=ProductControllerTest test
```

```bash
mvn -Dtest=ProductRepositoryTest test
```

## Optional Testcontainers Test

`ProductRepositoryPostgresTestcontainersTest` is included but disabled by default because it requires Docker.

To use it in a lesson:

1. Open the test class.
2. Remove `@Disabled`.
3. Make sure Docker Desktop is running.
4. Run the test from Eclipse or Maven.

Command:

```bash
mvn -Dtest=ProductRepositoryPostgresTestcontainersTest test
```

## Build Docker Image

```bash
docker build -t eclipse-spring-boot-retail-api:boot3 .
```

## Run Docker Container

```bash
docker run --rm -p 8080:8080 eclipse-spring-boot-retail-api:boot3
```

Test:

```bash
curl http://localhost:8080/api/products
```

## Run with Docker Compose and PostgreSQL

Start:

```bash
docker compose up --build
```

Test:

```bash
curl http://localhost:8080/api/products
```

Stop and remove containers:

```bash
docker compose down
```

Stop and remove containers plus PostgreSQL volume:

```bash
docker compose down -v
```

## Eclipse / STS Usage

### Import Project

In Eclipse or STS:

```text
File → Import → Maven → Existing Maven Projects → Select project folder → Finish
```

Then:

```text
Right-click project → Maven → Update Project
```

### Run in Plain Eclipse

```text
Open RetailApiApplication.java
Right-click → Run As → Java Application
```

### Debug in Plain Eclipse

```text
Set breakpoint in ProductController or InventoryService
Right-click RetailApiApplication.java
Debug As → Java Application
```

### Run from Boot Dashboard in STS / Spring Tools

```text
Window → Show View → Other → Boot Dashboard
Select eclipse-spring-boot-retail-api
Start / Stop / Restart / Debug
```

## Good Demo Files by Lesson

### Boot Dashboard

```text
RetailApiApplication.java
ProductController.java
InventoryController.java
```

### Navigation

```text
ProductController → ProductService → ProductRepository
InventoryController → InventoryService → ProductRepository + StockAdjustmentRepository
GlobalExceptionHandler
RetailProperties
```

### Code Completion and Validation

```text
ProductRequest.java
StockAdjustmentRequest.java
RetailProperties.java
application.properties
```

### Live Bean Wiring / Runtime Info

```text
ProductService.java
InventoryService.java
ProductRepository.java
StockAdjustmentRepository.java
Actuator endpoints
```

### Docker

```text
Dockerfile
.dockerignore
compose.yaml
application-docker.properties
```

### JUnit / Mockito / MockMvc

```text
ProductServiceTest.java
InventoryServiceTest.java
ProductControllerTest.java
InventoryControllerTest.java
ProductRepositoryTest.java
StockAdjustmentRepositoryTest.java
```

## Future Branches

Use tags for lesson checkpoints.

Use branches only for divergent tracks:

```text
boot4-migration
spring-ai
```

Suggested tags:

```text
l05-retail-api-base
l06-boot-dashboard
l07-navigation
l08-code-completion-validation
l09-live-bean-wiring
l10-dockerfile
l11-docker-compose
l12-junit-basics
l13-controller-service-repository-tests
```
