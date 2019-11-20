package pro.komdosh.lab5.agent

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

        val receiver = AID("Receiver", false)

        val action = Action()
        action.actor = receiver
        action.action = Register(
            Student("Tabakov", "4307"),
            Course("MAS Course", Instructor("Zhandarov", "CS"))
        )

        val msg = generateMsgRequest(receiver)

        manager.fillContent(msg, action)

        val b = SenderBehaviour(this, msg)
        addBehaviour(b)
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
