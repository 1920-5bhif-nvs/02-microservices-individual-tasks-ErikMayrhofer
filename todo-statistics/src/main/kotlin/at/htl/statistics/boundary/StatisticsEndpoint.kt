package at.htl.statistics.boundary

import org.eclipse.microprofile.faulttolerance.Fallback
import org.eclipse.microprofile.faulttolerance.Retry
import org.eclipse.microprofile.faulttolerance.Timeout
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Counted
import org.eclipse.microprofile.metrics.annotation.Timed
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Inject
import javax.json.Json
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("statistics")
open class StatisticsEndpoint {

    @Inject
    @field:RestClient
    lateinit var tr: TaskRequest

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(1000)
    @Retry(maxRetries = 3)
    @Fallback(fallbackMethod = "nullstatistics")
    @Counted(name = "http_statistics_total", description = "How many times were the statistics queried.")
    @Timed(name = "http_statistics_seconds", description = "How many milliseconds did the statistics take", unit = MetricUnits.SECONDS)
    fun statistics(): Response {
        val personTodoCounts = tr.tasks.groupBy {
            it.asJsonObject().getJsonObject("person").getJsonNumber("id").intValue()
        }.map {
            it.key to it.value.size
        }.toMap()

        return Response.ok(personTodoCounts).build()
    }

    fun nullstatistics(): Response {
        return Response.ok(Json.createObjectBuilder().build()).build()
    }
}