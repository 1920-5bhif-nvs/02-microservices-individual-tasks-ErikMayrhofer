package at.htl.health

import at.htl.statistics.boundary.TaskRequest
import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.HealthCheckResponseBuilder
import org.eclipse.microprofile.health.Liveness
import org.eclipse.microprofile.rest.client.inject.RestClient
import java.net.ConnectException
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.ProcessingException

@Liveness
@ApplicationScoped
open class TaskServiceHealthCheck: HealthCheck {

    @Inject
    @field:RestClient
    lateinit var tr: TaskRequest

    override fun call(): HealthCheckResponse {
        try {
            val tasks = tr.tasks
        }catch (ex: ProcessingException){
            return HealthCheckResponse.builder().name("TaskService").down().withData("cause", ex.message).build()
        }
        return HealthCheckResponse.builder().name("TaskService").up().build()
    }

}