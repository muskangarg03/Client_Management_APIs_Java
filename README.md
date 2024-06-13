## **DailyPe(NeoFirst) - Backend Task Client Management APIs**

## Objective:
Develop a set of RESTful APIs to manage client information for a financial organization. These APIs will handle the retrieval, creation,
updating, and deletion of client records, ensuring proper association with valid office entries. 
The implementation should utilizes Java (Spring Boot), be backed by a PostgreSQL and MySQL database, and a containerized application run on a local server (for Java).


## Tech Stack:
- **Programming Language**: *Java (Spring Boot)*
- **Databases**: *PostgreSQL and MySQL*
- **Containerization**: *Docker*
- **Server**: *Local Server for Java (Apache Tomcat)*
- **Testing**: *Postman*


## Features:
### 1. Client Retrieval
- **Endpoint**: `/clients`
- **Method**: `GET`
- **Description**: Fetch a list of all clients.

### 2. Client Retrieval By Id
- **Endpoint**: `/clients{id}`
- **Method**: `GET`
- **Description**: Fetch a client using its client id.

### 3. Client Creation
- **Endpoint**: `/clients`
- **Method**: `POST`
- **Description**: Create a new client record.
- **Payload**:

### 4. Client Update
- **Endpoint**: `/clients/{id}`
- **Method**: `PUT`
- **Description**: Update an existing client record.
- **Payload**:

### 5. Client Deletion
- **Endpoint**: `/clients/{id}`
- **Method**: `DELETE`
- **Description**: Delete a client record by ID.


## Setup and Installation:
### 1. Clone the Repository
### 2. Configure the Databases
- **PostgreSQL**: Set up your PostgreSQL database and update the `application.properties` file. <br>
  *Commands:*
```sql
CREATE TABLE office (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    opening_date DATE NOT NULL,
    hierarchy VARCHAR(50) NOT NULL,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES office(id)
);

INSERT INTO office (name, opening_date, hierarchy, parent_id) VALUES 
('Head Office', '2020-01-01', '1', NULL),
('Branch Office 1', '2021-02-01', '1.1', 1),
('Branch Office 2', '2021-03-01', '1.2', 1),
('Sub Branch 1.1', '2022-04-01', '1.1.1', 2);


CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    office_id BIGINT NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    external_id VARCHAR(100) UNIQUE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    activation_date DATE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO client (office_id, firstname, lastname, external_id, status, activation_date, created_date, last_modified_date) VALUES 
(1, 'John', 'Doe', 'JD12345', 'Active', '2023-01-01', '2023-01-01 12:00:00', '2023-06-01 12:00:00'),
(2, 'Jane', 'Smith', 'JS67890', 'Pending', NULL, '2023-02-01 12:00:00', '2023-06-01 12:00:00'),
(3, 'Alice', 'Johnson', 'AJ23456', 'Active', '2023-03-01', '2023-03-01 12:00:00', '2023-06-01 12:00:00'),
(4, 'Bob', 'Brown', 'BB34567', 'Closed', '2023-04-01', '2023-04-01 12:00:00', '2023-06-01 12:00:00');

SELECT * FROM office;
SELECT * FROM client;

DROP TABLE client;
```
  
- **MySQL**: Set up your MySQL database and update the `application.properties` file.<br>
  *Commands:*
```sql
show databases;
create database muskan;
use muskan;

CREATE TABLE office (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    opening_date DATE NOT NULL,
    hierarchy VARCHAR(50) NOT NULL,
    parent_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES office(id)
);

INSERT INTO office (name, opening_date, hierarchy, parent_id) VALUES 
('Head Office', '2020-01-01', '1', NULL),
('Branch Office 1', '2021-02-01', '1.1', 1),
('Branch Office 2', '2021-03-01', '1.2', 1),
('Sub Branch 1.1', '2022-04-01', '1.1.1', 2);


CREATE TABLE client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    office_id BIGINT NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    external_id VARCHAR(100) UNIQUE,
    status ENUM('PENDING', 'ACTIVE', 'CLOSED', 'REACTIVATED') NOT NULL DEFAULT 'PENDING',
    activation_date DATE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (office_id) REFERENCES office(id)
);

INSERT INTO client (office_id, firstname, lastname, external_id, status, activation_date, created_date, last_modified_date) VALUES 
(1, 'John', 'Doe', 'JD12345', 'Active', '2023-01-01', '2023-01-01 12:00:00', '2023-06-01 12:00:00'),
(2, 'Jane', 'Smith', 'JS67890', 'Pending', NULL, '2023-02-01 12:00:00', '2023-06-01 12:00:00'),
(3, 'Alice', 'Johnson', 'AJ23456', 'Active', '2023-03-01', '2023-03-01 12:00:00', '2023-06-01 12:00:00'),
(4, 'Bob', 'Brown', 'BB34567', 'Closed', '2023-04-01', '2023-04-01 12:00:00', '2023-06-01 12:00:00');

select * from office;
select * from client;

drop table client;
```

### 3. Build and Run with Docker
*DockerFile*
```groovy
FROM openjdk:23-jdk
EXPOSE 8080  
ADD target/springapp.jar springapp.jar
ENTRYPOINT ["java","-jar", "/springapp.jar"]
```

### 4. Access the Application
For Postresql : Open your browser and navigate to http://localhost:9090/clients. <br>
For MySQL : Open your browser and navigate to http://localhost:9000/clients.


## Database Configuration:
### PostgreSql:
```groovy
server.port=9090
spring.datasource.url=jdbc:postgresql://localhost:5432/muskan
spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Mysql:
```groovy
server.port=9000
spring.datasource.url=jdbc:mysql://localhost:3306/muskan
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Testing the APIs
To test the API endpoints, you can use tools like Postman or curl.
### Postman
1. Launch Postman and import the API collection or manually create requests for each endpoint.
2. Set the appropriate HTTP method, URL, headers, and body parameters for each request.
3. Send the request and inspect the response to verify the API functionality.





