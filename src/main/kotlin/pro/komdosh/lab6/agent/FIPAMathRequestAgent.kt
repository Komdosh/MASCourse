package pro.komdosh.lab6.agent

import jade.core.AID
import jade.core.Agent
import jade.domain.FIPANames
import jade.lang.acl.ACLMessage
import pro.komdosh.lab6.behaviour.FIPAMathRequestBehaviour
import pro.komdosh.lab6.behaviour.ResultBehaviour
import pro.komdosh.lab6.mathAgentsCount
import java.util.*


class FIPAMathRequestAgent : Agent() {

    private val results: Array<Double> = arrayOf(Double.MIN_VALUE, Double.MIN_VALUE)

    override fun setup() {
        require(!arguments.isNullOrEmpty()) {
            "You should add fourth strings Example:\n" +
                    "( subtract 12 11 )\n" +
                    "( multiply 14 4 )\n" +
                    "add \n" +
                    "12-11 + 14 * 4"
        }

        val args = arguments as Array<String>

        val firstAction = args[0]
        var msg = sendAction(firstAction)

        addBehaviour(FIPAMathRequestBehaviour(this, msg, 0, results))

        val secondAction = args[1]
        msg = sendAction(secondAction)

        addBehaviour(FIPAMathRequestBehaviour(this, msg, 1, results))

        addBehaviour(ResultBehaviour(this, results, args[2], args[3]))
    }

    fun sendAction(content: String): ACLMessage {
        val msg = ACLMessage(ACLMessage.REQUEST)
        msg.protocol = FIPANames.InteractionProtocol.FIPA_REQUEST
        // We want to receive a reply in 10 secs
        msg.replyByDate = Date(System.currentTimeMillis() + 10000)
        msg.content = content
        for (i in 0 until mathAgentsCount) {
            msg.addReceiver(AID("FIPAMathAgent$i", AID.ISLOCALNAME))
        }
        println("Agent $localName sending action: $content")
        return msg
    }
}

