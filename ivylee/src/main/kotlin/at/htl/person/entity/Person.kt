package at.htl.person.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*


@Entity
@NamedQuery(name = "person.findAll", query = "select p from Person p")
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null
){
    lateinit var firstName: String
    lateinit var lastName: String
}