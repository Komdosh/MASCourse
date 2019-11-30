package pro.komdosh.lab6.agent.example

import jade.core.Agent
import jade.domain.FIPANames
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate
import pro.komdosh.lab6.behaviour.example.FIPAResponseBehaviour

class FIPARequestResponderAgent : Agent() {
    override fun setup() {
        println("Agent $localName waiting for requests...")
        val template = MessageTemplate.and(
            MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
            MessageTemplate.MatchPerformative(ACLMessage.REQUEST)
        )
        addBehaviour(FIPAResponseBehaviour(this, template))
    }
}
