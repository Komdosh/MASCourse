package pro.komdosh.lab6.behaviour

import jade.core.Agent
import jade.lang.acl.ACLMessage
import jade.proto.AchieveREInitiator
import pro.komdosh.lab4.parseAnswer

class FIPAMathResultRequestBehaviour(
    agent: Agent,
    msg: ACLMessage,
    private val toEval: String
) : AchieveREInitiator(agent, msg) {

    override fun handleInform(inform: ACLMessage) {
        println("Agent ${inform.sender.name} successfully performed the requested action with content: ${inform.content}")
        println("$toEval = ${parseAnswer(inform)}")
    }

    override fun handleRefuse(refuse: ACLMessage) {
        println("Agent ${refuse.sender.name} refused to perform the requested action")
    }

    override fun handleFailure(failure: ACLMessage) {
        if (failure.sender == myAgent.ams) {
            // FAILURE notification from the JADE runtime: the receiver
            // does not exist
            println("Responder does not exist")
        } else {
            println("Agent ${failure.sender.name} failed to perform the requested action")
        }
    }
}

