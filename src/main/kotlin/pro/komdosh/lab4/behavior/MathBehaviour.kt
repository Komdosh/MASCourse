package pro.komdosh.lab4.behavior

import jade.core.behaviours.CyclicBehaviour
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate

class MathBehaviour : CyclicBehaviour() {

    private val actionsMap = mapOf<String, (Double, Double) -> Double>(
        Pair("add", Double::plus),
        Pair("subtract", Double::minus),
        Pair("multiply", Double::times),
        Pair("divide", Double::div)
    )

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

            val operation = actionsMap.keys.find {
                content.contains(it)
            }.orEmpty()

            val result = if (operation.isNotEmpty()) doMath(content, operation) else 0.0

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

    private fun getDouble(content: String, action: String, isSecond: Boolean = false): Double {
        var fromChar: Int = content.lastIndexOf(action) + action.length
        if (isSecond) {
            fromChar += content.substring(fromChar + 1).indexOf(" ")
        }
        val toChar: Int = content.substring(fromChar + 1).indexOf(if (isSecond) ")" else " ") + fromChar
        return content.substring(fromChar + 1, toChar + 1).toDouble()
    }

    private fun doMath(content: String, operation: String): Double {
        val firstD = getDouble(content, operation)
        val secondD = getDouble(content, operation, true)

        val result = actionsMap[operation]?.let {
            it(firstD, secondD)
        } ?: 0.0

        println("${agent.aid.localName} Making: $firstD $operation $secondD = $result")

        return result
    }
}




