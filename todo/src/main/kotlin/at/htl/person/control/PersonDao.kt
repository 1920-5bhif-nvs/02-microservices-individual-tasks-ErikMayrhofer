package at.htl.person.control

import at.htl.person.entity.Person
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
    open fun add(person: Person): Person {
        em.persist(person)
        return person
    }

    @Transactional
    open fun update(person: Person): Person {
        return em.merge(person)
    }

    @Transactional
    open fun delete(taskId: Long): Person {
        val toRemove = em.find(Person::class.java, taskId)
        em.remove(toRemove)
        return toRemove
    }
}