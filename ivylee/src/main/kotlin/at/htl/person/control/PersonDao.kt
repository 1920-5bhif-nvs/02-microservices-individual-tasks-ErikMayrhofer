package at.htl.person.control

import at.htl.person.entity.Person
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager

@ApplicationScoped
class PersonDao {
    @Inject
    lateinit var em: EntityManager

    fun findAll(): List<Person>{
        val result = em.createNamedQuery("person.findAll", Person::class.java)
        return result.resultList
    }
}