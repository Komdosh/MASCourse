package pro.komdosh.lab1.behavior

import jade.core.behaviours.SimpleBehaviour
import jade.lang.acl.ACLMessage
import pro.komdosh.lab1.agent.TalkerAgent

internal class TalkerBehaviour(private val agent: TalkerAgent) : SimpleBehaviour() {
    override fun action() {
        val msg = agent.receive()
        if (msg != null) {
            println("The following message is received:\n$msg")
            val sender = msg.sender
            println(
                "Sending the following reply to the agent " +
                        sender.toString() + " :"
            )
            val reply = ACLMessage(ACLMessage.INFORM)
            reply.language = "human-language"
            reply.encoding = "text/plain"
            reply.conversationId = "Conversation-ID#0001"
            reply.addReceiver(sender)
            reply.content = "Hi, friend!! Have a good day!!"
            println(reply.toString())
            agent.send(reply)
        }
    }

    override fun done(): Boolean {
        return false
    }
}
