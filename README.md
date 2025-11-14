# GestionConsultations

Spring Boot application for managing consultations.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

Or run the main class `GestionConsultationsApplication` from your IDE.

The application will start on `http://localhost:8080`

## Features

- Spring Boot 3.2.0
- Spring Data JPA for database operations
- H2 in-memory database (for development)
- RESTful web services support
- Bean validation support

## H2 Console

Access the H2 database console at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:consultationsdb`
- Username: `sa`
- Password: (leave empty)

## Project Structure

```
GestionConsultations/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/gestionconsultations/
│   │   │       └── GestionConsultationsApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/gestionconsultations/
│               └── GestionConsultationsApplicationTests.java
├── pom.xml
└── README.md
```

