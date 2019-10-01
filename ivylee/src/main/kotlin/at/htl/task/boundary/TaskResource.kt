package at.htl.task.boundary

import at.htl.task.control.TaskDao
import at.htl.task.entity.Task
import javax.inject.Inject
import javax.print.attribute.standard.Media
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/tasks")
open class TaskResource {

    @Inject
    lateinit var taskDao: TaskDao

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
}