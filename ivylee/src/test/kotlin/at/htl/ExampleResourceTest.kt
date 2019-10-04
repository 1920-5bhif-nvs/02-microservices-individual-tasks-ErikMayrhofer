package at.htl

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.h2.H2DatabaseTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.persistence.EntityManager

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource::class)
open class ExampleResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/persons")
          .then()
             .statusCode(200)
             .body("size()", `is`(0))
    }

}