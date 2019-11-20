package pro.komdosh.lab6.behaviour

import jade.core.Agent
import jade.lang.acl.ACLMessage
import jade.proto.AchieveREInitiator
import java.util.*

class FIPARequestBehaviour(agent: Agent, msg: ACLMessage, private var nResponders: Int = 0) :
    AchieveREInitiator(agent, msg) {
    override fun handleInform(inform: ACLMessage?) {
        println("Agent ${inform!!.sender.name} successfully performed the requested action")
    }

    override fun handleRefuse(refuse: ACLMessage?) {
        println("Agent ${refuse!!.sender.name} refused to perform the requested action")
        nResponders--
    }

    override fun handleFailure(failure: ACLMessage?) {
        if (failure!!.sender == myAgent.ams) {
            // FAILURE notification from the JADE runtime: the receiver
            // does not exist
            println("Responder does not exist")
        } else {
            println("Agent ${failure.sender.name} failed to perform the requested action")
        }
    }

    override fun handleAllResultNotifications(notifications: Vector<*>?) {
        if (notifications!!.size < nResponders) {
            // Some responder didn't reply within the specified timeout
            println("Timeout expired: missing ${nResponders - notifications.size} responses")
        }
    }
}

