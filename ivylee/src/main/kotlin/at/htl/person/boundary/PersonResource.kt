package at.htl.person.boundary

import at.htl.person.control.PersonDao
import at.htl.person.entity.Person
import at.htl.task.control.TaskDao
import at.htl.task.entity.Task
import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/persons")
open class PersonResource {
    @Inject
    lateinit var taskDao: PersonDao

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<Person>{
        return taskDao.findAll()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(task: Person): Person {
        return taskDao.add(task)
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    fun update(task: Person): Person {
        return taskDao.update(task)
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: Long, task: Person): Person {
        task.id = id
        return taskDao.update(task)
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(task: Person): Person {
        return taskDao.delete(task.id!!)
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(@PathParam("id") id: Long): Person {
        return taskDao.delete(id)
    }

    @PostConstruct
    @Transactional
    fun setup(){
        val persons = arrayOf(
                Person().apply { firstName="Abc"; lastName="Def" },
                Person().apply { firstName="Haa"; lastName="Eee" }
        )
    }
}