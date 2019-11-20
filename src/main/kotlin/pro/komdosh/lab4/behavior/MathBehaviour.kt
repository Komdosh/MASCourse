package pro.komdosh.lab4.behavior

import jade.core.behaviours.CyclicBehaviour
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate
import pro.komdosh.doMath
import pro.komdosh.getAvailableMathActions

class MathBehaviour : CyclicBehaviour() {

    override fun action() {
        val mt = buildMathRequestMessageTemplate()

        receiveMathRequest(mt)
    }

    private fun buildMathRequestMessageTemplate(): MessageTemplate? {
        return MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
            MessageTemplate.and(
                MessageTemplate.MatchLanguage("predicate"),
                MessageTemplate.MatchOntology("math-ontology")
            )
        )
    }

    private fun receiveMathRequest(mt: MessageTemplate?) {
        val msg = agent.blockingReceive(mt)
        if (msg != null) {
            block(5000)
            println("${agent.aid.localName} Message: ${msg.content} was sent by ${msg.sender}")
            val content = msg.content

            val operation = getAvailableMathActions().find {
                content.contains(it)
            }.orEmpty()

            val result = if (operation.isNotEmpty()) doMath(agent.aid.localName, content, operation) else 0.0

            val reply = ACLMessage(ACLMessage.INFORM)
            reply.addReceiver(msg.sender)
            reply.language = "predicate"
            reply.ontology = "math-ontology"
            reply.replyWith = msg.replyWith
            reply.content = "($result)"
            println("${agent.aid.localName} Sent message: $reply")
            agent.send(reply)
        }
    }


}




