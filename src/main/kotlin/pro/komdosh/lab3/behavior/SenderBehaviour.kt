package pro.komdosh.lab3.behavior

import jade.core.AID
import jade.core.behaviours.SimpleBehaviour
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate

class SenderBehaviour(
    var finished: Boolean = false
) : SimpleBehaviour() {

    override fun action() {
        sendMsg()

        receiveAnswer()
    }

    private fun receiveAnswer() {
        val mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.AGREE),
            MessageTemplate.MatchLanguage("Prolog")
        )
        val receivedMsg = agent.blockingReceive(mt)
        if (receivedMsg != null) {
            println("=======================================")
            println("Message: \"${receivedMsg.content}\" was sent by ${receivedMsg.sender}")
            println("=======================================")
        }
    }

    private fun sendMsg() {
        val msg = buildSendMsg(ACLMessage(ACLMessage.QUERY_IF), AID("Receiver", false))

        agent.send(msg)
        println("Request for receiver sent")
    }

    private fun buildSendMsg(msg: ACLMessage, receiverAID: AID): ACLMessage {
        msg.addReceiver(receiverAID)

        println("Sending the following message to the agent $receiverAID")

        msg.language = "Prolog"
        msg.ontology = "Parents"
        msg.encoding = "text/plain"
        msg.conversationId = "Conversation-ID#0001"
        msg.content = "Son(John, Bob)"
        println(msg)
        return msg
    }

    override fun done(): Boolean {
        println("Sender Behaviour I'm done")
        finished = true
        return finished
    }
}
