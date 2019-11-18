package pro.komdosh.lab3.behavior

import jade.core.behaviours.SimpleBehaviour
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate
import pro.komdosh.lab3.agent.ReceiverAgent

class ReceiverBehaviour(
    private val agent: ReceiverAgent,
    var finished: Boolean = false
) : SimpleBehaviour() {

    override fun action() {
        val mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            MessageTemplate.MatchLanguage("human-language")
        )

        val msg = agent.blockingReceive(mt)
        if (msg != null) {
            println("Message: ${msg.content} was sent by ${msg.sender}")
        }
    }

    override fun done(): Boolean {
        println("Receiver Behaviour I'm done")
        finished = true
        return finished
    }
}
