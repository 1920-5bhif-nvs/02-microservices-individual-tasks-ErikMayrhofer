package at.htl.person.control

import at.htl.person.entity.Person
import at.htl.task.entity.Task
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
open class PersonDao {
    @Inject
    open lateinit var em: EntityManager

    open fun findAll(): List<Person>{
        val e = em.createNamedQuery("person.findAll", Person::class.java)
        return e.resultList
    }

    @Transactional
    open fun add(task: Person): Person {
        em.persist(task)
        return task
    }

    @Transactional
    open fun update(task: Person): Person {
        return em.merge(task)
    }

    @Transactional
    open fun delete(taskId: Long): Person {
        val toRemove = em.find(Person::class.java, taskId)
        em.remove(toRemove)
        return toRemove
    }
}