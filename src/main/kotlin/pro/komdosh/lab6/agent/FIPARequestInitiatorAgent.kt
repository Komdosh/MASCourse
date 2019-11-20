package pro.komdosh.lab6.agent

import jade.core.AID
import jade.core.Agent
import jade.domain.FIPANames
import jade.lang.acl.ACLMessage
import pro.komdosh.lab6.behaviour.FIPARequestBehaviour
import java.util.*

class FIPARequestInitiatorAgent : Agent() {
    private var nResponders: Int = 0
    override fun setup() {

        if (arguments.isNullOrEmpty()) {
            println("No responder specified.")
            return
        }

        nResponders = arguments.size
        println("Requesting dummy-action to $nResponders responders.")

        // Fill the REQUEST message
        val msg = ACLMessage(ACLMessage.REQUEST)
        arguments.forEach {
            msg.addReceiver(AID(it as String, AID.ISLOCALNAME))
        }

        msg.protocol = FIPANames.InteractionProtocol.FIPA_REQUEST
        // We want to receive a reply in 10 secs
        msg.replyByDate = Date(System.currentTimeMillis() + 10000)
        msg.content = "dummy-action"

        addBehaviour(FIPARequestBehaviour(this, msg, nResponders))
    }
}
