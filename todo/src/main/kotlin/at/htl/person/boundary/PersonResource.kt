package at.htl.person.boundary

import at.htl.person.control.PersonDao
import at.htl.person.entity.Person
import io.quarkus.runtime.StartupEvent
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/persons")
open class PersonResource {
    @Inject
    lateinit var personDao: PersonDao

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<Person>{
        return personDao.findAll()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(task: Person): Person {
        return personDao.add(task)
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    fun update(task: Person): Person {
        return personDao.update(task)
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: Long, task: Person): Person {
        task.id = id
        return personDao.update(task)
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(task: Person): Person {
        return personDao.delete(task.id!!)
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(@PathParam("id") id: Long): Person {
        return personDao.delete(id)
    }
}