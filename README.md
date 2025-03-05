# Parsing API

## Project Overview
This project is a **Parsing API** built using **Java Spring Boot**. The API performs data parsing operations from various sources and provides endpoints to access the parsed data.

## Features
- Data parsing from external sources
- CRUD operations for parsed data
- MySQL database
- Validation and exception handling
- Swagger API documentation

## Technologies Used
- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL
- Swagger OpenAPI
- Lombok
- Maven

## Installation and Running the Project

### Prerequisites
- Java 17+
- Maven
- MySQL Server

### Clone the Repository
```bash
git clone https://github.com/SobachkaKoli/parsing.git
cd parsing
```

### Configure Database
Create a MySQL database:
```sql
CREATE DATABASE parsing_db;
```
Update **application.properties** with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parsing_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will be available at:
```
http://localhost:8080
```

## API Endpoints
| Method | Endpoint    | Description       |
|--------|------------|------------------|
| GET    | /data      | Get all parsed data |
| POST   | /data      | Parse new data     |
| PUT    | /data/{id} | Update parsed data |
| DELETE | /data/{id} | Delete parsed data |

## Swagger API Documentation
Swagger UI is available at:
```
http://localhost:8080/swagger-ui/index.html
```

## Author
[Vladyslav Sydiuk](https://github.com/SobachkaKoli)

## License
This project is licensed under the MIT License.

