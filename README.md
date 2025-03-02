# Spring Boot 3 Microservices

This project demonstrates a set of microservices developed using Spring Boot 3, designed to handle various business domains such as orders, inventory, and notifications. The project is structured with individual services that can be deployed independently, and it supports integration through an API Gateway. The services are containerized using Docker and can be deployed with Kubernetes.

## Project Structure

- **API Gateway**: A service that routes requests to appropriate microservices.
- **Order Service**: Manages order-related operations.
- **Inventory Service**: Handles product inventory management.
- **Notification Service**: Sends notifications related to order status.
- **Product Service**: Manages product catalog information.

## Features

- Microservices architecture with Spring Boot 3.
- API Gateway for routing requests.
- Docker support for containerization.
- Kubernetes configuration for orchestration.
- Maven-based project with dependencies managed via `pom.xml`.

## Requirements

Before running the project, make sure you have the following installed:

- Java 17 or later
- Maven
- Docker
- Kubernetes (optional, for deployment)

## Setup and Installation

### Clone the repository

```bash
git clone https://github.com/nilesh212/spring-boot-3-microservices.git
cd spring-boot-3-microservices
