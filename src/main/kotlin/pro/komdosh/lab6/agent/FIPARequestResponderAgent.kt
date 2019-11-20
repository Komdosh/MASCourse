package pro.komdosh.lab6.agent

import jade.core.Agent
import jade.domain.FIPAAgentManagement.FailureException
import jade.domain.FIPAAgentManagement.NotUnderstoodException
import jade.domain.FIPAAgentManagement.RefuseException
import jade.domain.FIPANames
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate
import jade.proto.AchieveREResponder

class FIPARequestResponderAgent : Agent() {
    override fun setup() {
        println("Agent $localName waiting for requests...")
        val template = MessageTemplate.and(
            MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST)
        )
        addBehaviour(object : AchieveREResponder(this, template) {
            @Throws(NotUnderstoodException::class, RefuseException::class)
            override fun prepareResponse(request: ACLMessage?): ACLMessage {
                println("Agent $localName: REQUEST received from ${request!!.sender.name}. Action is ${request.content}")
                if (checkAction()) {
                    // We agree to perform the action. Note that in the FIPA-Request
                    // protocol the AGREE message is optional. Return null if you
                    // don't want to send it.
                    println("Agent $localName: Agree")
                    val agree = request.createReply()
                    agree.performative = ACLMessage.AGREE
                    return agree
                } else {
                    // We refuse to perform the action
                    println("Agent $localName: Refuse")
                    throw RefuseException("check-failed")
                }
            }

            @Throws(FailureException::class)
            override fun prepareResultNotification(
                request: ACLMessage?,
                response: ACLMessage?
            ): ACLMessage {
                if (performAction()) {
                    println("Agent $localName: Action successfully performed")
                    val inform = request!!.createReply()
                    inform.performative = ACLMessage.INFORM
                    return inform
                } else {
                    println("Agent $localName: Action failed")
                    throw FailureException("unexpected-error")
                }
            }
        })
    }

    private fun checkAction(): Boolean {
        // Simulate a check by generating a random number
        return Math.random() > 0.2
    }

    private fun performAction(): Boolean {
        // Simulate action execution by generating a random number
        return Math.random() > 0.2
    }
}
