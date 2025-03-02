package com.techie.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection   //This annotation is used to automatically set mongodb uri to the mongodbcontainer
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		/**
		 * REST-Assured is a Java library for testing RESTful web services.
		 * It is used to invoke REST web services and validate requests & responses.
		 * REST-Assured is an open-source API test automation tool for testing the rest services.
		 */
		RestAssured.baseURI="http://localhost";
		RestAssured.port=port;
	}

	static{
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody= """
				{
				    "name":"Pen",
				    "description":"WRite like pro!",
				    "price":20
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name",Matchers.equalTo("Pen"))
				.body("description",Matchers.equalTo("WRite like pro!"))
				.body("price",Matchers.equalTo(20));
	}

}
