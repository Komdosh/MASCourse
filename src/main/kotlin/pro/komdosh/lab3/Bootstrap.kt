package pro.komdosh.lab3

import pro.komdosh.attachSniffer
import pro.komdosh.createMainContainer
import pro.komdosh.lab3.agent.ReceiverAgent
import pro.komdosh.lab3.agent.SenderAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    val receiver = cc.createNewAgent("Receiver", ReceiverAgent::class.java.name, null)
    val sender = cc.createNewAgent("Sender", SenderAgent::class.java.name, null)

    attachSniffer(cc, listOf(receiver, sender))

    receiver.start() //send message after sniffer is attached
    sender.start()
}




