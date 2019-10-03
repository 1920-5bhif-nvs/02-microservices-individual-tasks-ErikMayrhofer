package at.htl.task.entity

import at.htl.person.entity.Person
import java.time.Instant
import javax.persistence.*

@Entity
@NamedQuery(name="task.findAll", query = "select task from Task task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
) {
    lateinit var text: String

    // This is the day this task is schedule to be done
    var bigDay: Instant? = null

    var done: Boolean = false

    @ManyToOne
    lateinit var person: Person
}