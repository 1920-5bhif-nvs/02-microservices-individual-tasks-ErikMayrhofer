package at.htl.task.boundary

import at.htl.task.control.TaskDao
import at.htl.task.entity.Task
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/tasks")
class TaskResource {

    @Inject
    lateinit var taskDao: TaskDao

}