package pro.komdosh.lab3

import createMainContainer
import jade.core.AID
import jade.domain.JADEAgentManagement.SniffOn
import pro.komdosh.lab3.agent.ReceiverAgent
import pro.komdosh.lab3.agent.SenderAgent

fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false, withSniffer = true)



    cc.createNewAgent("Receiver", ReceiverAgent::class.java.name, null).start()

    cc.createNewAgent("Sender", SenderAgent::class.java.name, null).start()
    val snif = SniffOn()

    snif.sniffer = AID("Sniffer", false)
    snif.addSniffedAgents(AID("Receiver", false))
}


