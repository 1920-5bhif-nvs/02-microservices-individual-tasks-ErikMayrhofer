package at.htl.task.control

import at.htl.person.entity.Person
import at.htl.task.entity.Task
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
open class TaskDao {

    @Inject
    open lateinit var em: EntityManager

    open fun findAll(): List<Task>{
        val e = em.createNamedQuery("task.findAll", Task::class.java)
        return e.resultList
    }

    @Transactional
    open fun add(task: Task): Task {
        em.persist(task)
        em.flush()
        em.refresh(task)
        return task
    }

    @Transactional
    open fun update(task: Task): Task {
        return em.merge(task)
    }

    @Transactional
    open fun delete(taskId: Long): Task {
        val toRemove = em.find(Task::class.java, taskId)
        em.remove(toRemove)
        return toRemove
    }
}