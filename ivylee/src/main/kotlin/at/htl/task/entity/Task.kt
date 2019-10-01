package at.htl.task.entity

import at.htl.person.entity.Person
import java.time.Instant
import javax.persistence.*

@Entity
@NamedQuery(name="task.findAll", query = "select task from Task task")
open class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    open lateinit var text: String

    // This is the day this task is schedule to be done
    open var bigDay: Instant? = null

    open var done: Boolean = false

    @ManyToOne
    open lateinit var person: Person
}