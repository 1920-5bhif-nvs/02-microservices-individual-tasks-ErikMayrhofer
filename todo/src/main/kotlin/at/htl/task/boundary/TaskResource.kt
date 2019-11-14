package at.htl.task.boundary

import at.htl.person.control.PersonDao
import at.htl.person.entity.Person
import at.htl.task.control.TaskDao
import at.htl.task.entity.Task
import io.quarkus.runtime.StartupEvent
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/tasks")
open class TaskResource {

    @Inject
    lateinit var taskDao: TaskDao
    @Inject
    lateinit var personDao: PersonDao

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<Task>{
        return taskDao.findAll()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(task: Task): Task {
        return taskDao.add(task)
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    fun update(task: Task): Task {
        return taskDao.update(task)
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: Long, task: Task): Task {
        task.id = id
        return taskDao.update(task)
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(task: Task): Task {
        return taskDao.delete(task.id!!)
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(@PathParam("id") id: Long): Task {
        return taskDao.delete(id)
    }

    fun genTestData(@Observes ev: StartupEvent): Response{
        val persons = arrayOf(
            Person().apply { firstName="Peter"; lastName="Muster" },
            Person().apply { firstName="Klaus"; lastName="Dieter" }
        )
        persons.forEach { personDao.add(it) }

        val tasks = (1..10).map {
            Task().apply {text="TestTask_$it"; person=persons.random(); bigDay=Date.from(Instant.now().plus(it.toLong(), ChronoUnit.DAYS)) }
        }
        tasks.forEach { taskDao.add(it) }

        return Response.ok("${persons.size} Persons and ${tasks.size} Tasks added.").build()
    }
}