package at.htl.task.control

import at.htl.task.entity.Task
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager

@ApplicationScoped
open class TaskDao {

    @Inject
    open lateinit var em: EntityManager

    fun findAll(): List<Task>{
        val e = em.createNamedQuery("task.findAll", Task::class.java)
        return e.resultList
    }
}