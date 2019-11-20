package pro.komdosh.lab6.behaviour

import jade.core.Agent
import jade.domain.FIPAAgentManagement.FailureException
import jade.domain.FIPAAgentManagement.NotUnderstoodException
import jade.domain.FIPAAgentManagement.RefuseException
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate
import jade.proto.AchieveREResponder
import pro.komdosh.doMath
import pro.komdosh.getAvailableMathActions

class FIPAMathResponseBehaviour(agent: Agent, template: MessageTemplate) : AchieveREResponder(agent, template) {
    @Throws(NotUnderstoodException::class, RefuseException::class)
    override fun prepareResponse(request: ACLMessage): ACLMessage {
        println("Agent ${agent.localName}: REQUEST received from ${request.sender.name}. Action is ${request.content}")
        if (checkAction()) {
            // We agree to perform the action. Note that in the FIPA-Request
            // protocol the AGREE message is optional. Return null if you
            // don't want to send it.
            println("Agent ${agent.localName}: Agree")
            val agree = request.createReply()
            agree.performative = ACLMessage.INFORM

            val content = request.content
            val operation = getAvailableMathActions().find {
                content.contains(it)
            }.orEmpty()
            val result = if (operation.isNotEmpty()) doMath(
                agent.aid.localName,
                content,
                operation
            ) else throw NotUnderstoodException("check-failed")
            agree.content = "($result)"

            return agree
        } else {
            // We refuse to perform the action
            println("Agent ${agent.localName}: Refuse")
            throw RefuseException("check-failed")
        }
    }

    @Throws(FailureException::class)
    override fun prepareResultNotification(
        request: ACLMessage,
        response: ACLMessage
    ): ACLMessage {
        if (performAction()) {
            println("Agent ${agent.localName}: Action successfully performed")
            val inform = request.createReply()
            inform.performative = ACLMessage.INFORM
            return inform
        } else {
            println("Agent ${agent.localName}: Action failed")
            throw FailureException("unexpected-error")
        }
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

