package at.htl

import at.htl.person.control.PersonDao
import at.htl.person.entity.Person
import at.htl.task.control.TaskDao
import at.htl.task.entity.Task
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.Initialized
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.inject.Singleton

@ApplicationScoped
open class DbSetup {

    @Inject
    open lateinit var personDao: PersonDao

    @Inject
    open lateinit var taskDao: TaskDao

    open fun main(@Observes @Initialized(ApplicationScoped::class) init: Any?){
//        val persons = arrayOf(
//                Person().apply { firstName="Peter"; lastName="Muster" },
//                Person().apply { firstName="Klaus"; lastName="Dieter" }
//        )
//        persons.forEach { personDao.add(it) }
//
//        val tasks = (1..10).map {
//            Task().apply { text="TestTask_$it"; person=persons.random() }
//        }
//        tasks.forEach { taskDao.add(it) }
    }
}