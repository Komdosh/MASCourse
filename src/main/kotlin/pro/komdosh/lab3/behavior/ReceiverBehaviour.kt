package pro.komdosh.lab3.behavior

import jade.core.AID
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
            MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF),
            MessageTemplate.MatchLanguage("Prolog")
        )

        val receivedMsg = agent.blockingReceive(mt)
        if (receivedMsg != null) {
            println("=======================================")
            println("Message: \"${receivedMsg.content}\" was sent by ${receivedMsg.sender}")
            println("=======================================")
        }

        val msg = buildAnswerMsg(ACLMessage(ACLMessage.AGREE), AID("Sender", false))
        agent.send(msg)
        println("Response for sender sent")
    }

    private fun buildAnswerMsg(msg: ACLMessage, receiverAID: AID): ACLMessage {
        msg.addReceiver(receiverAID)

        println("Sending the following message to the agent $receiverAID")

        msg.language = "Prolog"
        msg.ontology = "Parents"
        msg.encoding = "text/plain"
        msg.conversationId = "Conversation-ID#0001"
        msg.content = "It is right, he is son"
        println(msg)
        return msg
    }

    override fun done(): Boolean {
        println("Receiver Behaviour I'm done")
        finished = true
        return finished
    }
}
