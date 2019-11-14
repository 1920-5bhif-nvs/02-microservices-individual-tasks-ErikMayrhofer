package at.htl

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.h2.H2DatabaseTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.json.Json
import javax.json.JsonObject
import javax.persistence.EntityManager

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource::class)
open class ExampleResourceTest {

    @Test
    fun test_person_data() {
        given()
          .`when`().get("/persons")
          .then()
             .statusCode(200)
             .body("size()", `is`(2))
    }

    @Test
    fun test_task_data() {
        given().log().all()
                .`when`().get("/tasks")
                .then()
                .statusCode(200)
                .body("size()", `is`(10))
    }

    @Test
    fun test_add_person() {

        val person = Json.createObjectBuilder()
                .add("firstName", "Testi")
                .add("lastName", "Tester".toString())
                .build()

        given()
                .`when`().get("/persons")
                .then()
                .statusCode(200)
                .body("size()", `is`(2))

        given().log().all().`when`().header("Content-Type", "application/json").body(person.toString()).post("/persons")
                .then()
                .statusCode(200)
                .body("firstName", `is`("Testi"))
                .body("lastName", `is`("Tester"))
                .body("id", notNullValue())

        given()
                .`when`().get("/persons")
                .then()
                .statusCode(200)
                .body("size()", `is`(3))
    }

}