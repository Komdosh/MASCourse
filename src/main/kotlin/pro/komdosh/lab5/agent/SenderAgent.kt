package pro.komdosh.lab5.agent

import jade.content.AgentAction
import jade.content.ContentManager
import jade.content.lang.sl.SLCodec
import jade.content.onto.basic.Action
import jade.core.AID
import jade.core.Agent
import jade.core.behaviours.SenderBehaviour
import jade.lang.acl.ACLMessage
import pro.komdosh.lab5.ontology.*

class SenderAgent : Agent() {
    private val manager = contentManager as ContentManager
    private val codec = SLCodec()
    private val ontology = StudentOntology.instance

    override fun setup() {
        manager.registerLanguage(codec)
        manager.registerOntology(ontology)
        println("Sender agent $name is started.")

        val receiver = AID("Receiver", AID.ISLOCALNAME)

        val register = Register(
            Student("Tabakov", "4307"),
            Course("MAS Course", Instructor("Zhandarov", "CS"))
        )
        sendAction(register, receiver)

        val unregister = Unregister(
            Student("Tabakov", "4307"),
            Course("MAS Course", Instructor("Zhandarov", "CS"))
        )
        sendAction(unregister, receiver)
    }

    private fun sendAction(agentAction: AgentAction, receiver: AID) {
        val action = Action()
        action.actor = receiver
        action.action = agentAction

        val msg = generateMsgRequest(receiver)

        manager.fillContent(msg, action)

        addBehaviour(SenderBehaviour(this, msg))
        println("The following message is being sent:\n$msg")
    }

    private fun generateMsgRequest(receiver: AID): ACLMessage {
        val msg = ACLMessage(ACLMessage.REQUEST)
        msg.sender = aid
        msg.addReceiver(receiver)
        msg.ontology = ontology.name
        msg.language = codec.name
        return msg
    }
}
