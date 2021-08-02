package dev.yadi

import dev.yadi.model.request.Greetings
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@QuarkusTest
class EntryPointTest {

    @Test
    fun testHealthCheckPoint() {
        given()
          .`when`().get("/health")
          .then()
             .statusCode(200)
             .assertThat()
             .body("path", equalTo("/health"))
             .body("res", equalTo("successfully reached health checkpoint"))
    }

    @Test
    fun testGreetingsPostRequest(){
        val requestBody = Greetings("Mr", "Quarkus")
        given()
            .body(requestBody)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
            .`when`().post("/greetings")
            .then()
            .statusCode(201)
            .assertThat()
            .body(containsString("Greetings there Mr.Quarkus"))
    }

    @Test
    fun testGreetingsWithParam(){
        val requestBody = Greetings("Mr", "Quarkus")
        given()
            .body(requestBody)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
            .`when`().post("/greetings/ydot19")
            .then()
            .statusCode(201)
            .assertThat()
            .body(containsString("Greetings ydot19 - From Mr.Quarkus"))
    }

}