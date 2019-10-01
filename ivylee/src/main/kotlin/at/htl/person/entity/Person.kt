package at.htl.person.entity

import javax.persistence.*


@Entity
@NamedQuery(name = "person.findAll", query = "select p from Person p")
open class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    open lateinit var firstName: String
    open lateinit var lastName: String
}