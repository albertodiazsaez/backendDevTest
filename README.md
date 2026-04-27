# Similar Product API

This project is a Spring Boot REST API that returns products similar to a given product ID.

---

## Features

- Retrieve similar products by product ID, aggregated from Existing API
- In-memory caching for improved performance
- Global exception handling
- OpenAPI / Swagger documentation

---

## Versions

- Java 21
- Spring Boot 3
- Spring Web (MVC)
- Spring Cache
- Spring RestClient

---

## Configuration

Set the external API URL in `application.properties`:

```properties
env.existing-api-url=http://localhost:3001
```

---

##  How to Run the Application

### 1. Clone the repository

```bash
git clone https://github.com/albertodiazsaez/backendDevTest
cd backendDevTest
```

---

### 2. Build the project

Make sure both JRE and JDK are installed on your system.

```bash
mvn clean install
```

---

### 3. Run the application

```bash
mvn spring-boot:run
```

Or run the JAR:

```bash
java -jar target/similarItemApi-0.0.1-SNAPSHOT.jar
```

---

##  API Endpoints

(Swagger avaiable at http://localhost:5000/swagger-ui/index.html)

(OpenAPI avaiable at http://localhost:5000/v3/api-docs)

### Get Similar Products

```http
GET /product/{productId}/similar
```

### Example

```http
GET http://localhost:5000/product/123/similar
```

### Response

```json
[
  {
    "id": "2",
    "name": "Dress",
    "price": 19.99,
    "availability": true
  },
  {
    "id": "3",
    "name": "Blazer",
    "price": 29.99,
    "availability": false
  },
  {
    "id": "4",
    "name": "Boots",
    "price": 39.99,
    "availability": true
  }
]
```

---



##  Error Handling

The API includes global exception handling for:

- External API failures (5xx)
- Unexpected server errors
https://github.com/albertodiazsaez/backendDevTest