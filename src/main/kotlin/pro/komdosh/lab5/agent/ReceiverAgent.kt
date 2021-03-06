package pro.komdosh.lab5.agent

import jade.content.ContentManager
import jade.content.lang.sl.SLCodec
import jade.content.onto.basic.Action
import jade.core.Agent
import jade.lang.acl.MessageTemplate
import pro.komdosh.lab5.ontology.Register
import pro.komdosh.lab5.ontology.StudentOntology
import pro.komdosh.lab5.ontology.Unregister

class ReceiverAgent : Agent() {
    private val manager = contentManager as ContentManager
    private val codec = SLCodec()
    private val ontology = StudentOntology.instance

    override fun setup() {
        manager.registerLanguage(codec)
        manager.registerOntology(ontology)
        println("Receiver agent $name is started.")

        receiveActionRequest()
        receiveActionRequest()
    }

    private fun receiveActionRequest() {
        val mt = MessageTemplate.MatchOntology(ontology.name)
        val receiveMsg = blockingReceive(mt)
        println("The following message is received:\n$receiveMsg")

        val action = manager.extractContent(receiveMsg) as Action

        when (action.action) {
            is Register -> {
                val proposition = action.action as Register
                println("Course information received.")
                val course = proposition.course
                println("Course name: ${course.name}, Instructor: ${course.instructor.name}")
            }
            is Unregister -> {
                val proposition = action.action as Unregister
                println("Course information received.")
                val course = proposition.course
                println("Course name: ${course.name}, Instructor: ${course.instructor.name}")
            }
        }
    }
}
