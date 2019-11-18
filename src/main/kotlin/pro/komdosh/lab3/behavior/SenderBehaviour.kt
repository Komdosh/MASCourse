package pro.komdosh.lab3.behavior

import jade.core.AID
import jade.core.behaviours.SimpleBehaviour
import jade.lang.acl.ACLMessage
import pro.komdosh.lab3.agent.SenderAgent

class SenderBehaviour(
    private val agent: SenderAgent,
    var finished: Boolean = false
) : SimpleBehaviour() {

    override fun action() {
        val msg = ACLMessage(ACLMessage.INFORM)

        val receiver = "Receiver"
        println("Receiver agent is $receiver")

        val receiverAID = AID(receiver, false)
        msg.addReceiver(receiverAID)

        println("Sending the following message to the agent $receiverAID")

        msg.language = "human-language"
        msg.encoding = "text/plain"
        msg.conversationId = "Conversation-ID#0001"
        msg.content = "Hi, friend!! Have a nice day!!"
        println(msg)

        agent.send(msg)
        println("Message sent")
    }

    override fun done(): Boolean {
        println("Sender Behaviour I'm done")
        finished = true
        return finished
    }
}
