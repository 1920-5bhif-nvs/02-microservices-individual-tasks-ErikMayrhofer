package at.htl.task.entity

import at.htl.person.entity.Person
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.Instant
import java.util.*
import javax.persistence.*
import kotlin.properties.Delegates

@Entity
@NamedQuery(name="task.findAll", query = "select task from Task task")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property="id")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
) {
    lateinit var text: String

    // This is the day this task is schedule to be done
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy")
    var bigDay: Date? = null

    var done: Boolean = false

//    @JoinColumn(name = "personId", referencedColumnName = "id")
    @ManyToOne
//    @Cascade(value = [CascadeType.REFRESH])
    @Cascade(value = [])
    lateinit var person: Person

//    @Column(name = "personId", updatable = false, insertable = false)
//    var person_fk: Long? = null
}