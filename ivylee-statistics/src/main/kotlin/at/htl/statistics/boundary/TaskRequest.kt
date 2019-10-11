package at.htl.statistics.boundary

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.json.JsonArray
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@RegisterRestClient
interface TaskRequest {

    @get:GET
    @get:Produces(MediaType.APPLICATION_JSON)
    @get:Path("/tasks")
    val loans: JsonArray
}