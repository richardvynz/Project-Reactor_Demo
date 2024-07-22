# Reactive_Demo

### Welcome to Reactive_Demo: A Showcase of Reactive Programming with Spring WebFlux and MongoDB

Reactive_Demo is a Spring Boot application that exemplifies the power and efficiency of Reactive Programming. Designed to manage a dynamic product catalog, this project leverages Spring WebFlux and MongoDB to deliver a high-performance, non-blocking solution for CRUD operations.

#### Features:

- **Reactive Programming**: Embrace the future with an event-driven, non-blocking architecture that ensures high throughput and scalability.
- **Comprehensive CRUD Operations**: Perform Create, Read, Update, and Delete operations with ease, all exposed through well-designed RESTful endpoints.
- **Spring WebFlux Integration**: Benefit from the advanced features of Spring WebFlux, providing a reactive and resilient web application experience.
- **MongoDB Backend**: Utilize the power and flexibility of MongoDB for efficient data storage and retrieval, ensuring optimal performance for your product catalog.

Reactive_Demo is your gateway to mastering modern development practices. Explore the capabilities of Reactive Programming and transform the way you build applications with this innovative and inspiring project.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Contributing](#contributing)

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11 or later
- Maven 3.6 or later
- MongoDB

### Running the Application

1. Clone the repository:

   ```sh
   git clone https://github.com/richardvynz/Project-Reactor_Demo.git
   cd Project-Reactor_Demo
2. Build the project:
mvn clean install

3. Run the application:
mvn spring-boot:run

## Endpoints

The application exposes the following endpoints:

### Get All Products

- **URL**: `/products`
- **Method**: `GET`
- **Response**: `Flux<ProductDTO>`

### Get Product by ID

- **URL**: `/products/{id}`
- **Method**: `GET`
- **Response**: `Mono<ProductDTO>`

### Get Products in a Price Range

- **URL**: `/products/product-range?min={min}&max={max}`
- **Method**: `GET`
- **Response**: `Flux<ProductDTO>`

### Save a Product

- **URL**: `/products`
- **Method**: `POST`
- **Body**: `Mono<ProductDTO>`
- **Response**: `Mono<ProductDTO>`

### Update a Product

- **URL**: `/products/update/{id}`
- **Method**: `PUT`
- **Body**: `Mono<ProductDTO>`
- **Response**: `Mono<ProductDTO>`

### Delete a Product

- **URL**: `/products/delete/{id}`
- **Method**: `DELETE`
- **Response**: `Mono<Void>`


## Testing

The project includes unit tests for the `ProductController` using Spring WebFlux's `WebTestClient`.

Run the tests using Maven:

```sh
mvn test

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss your ideas.

### Steps to Contribute

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new Pull Request

