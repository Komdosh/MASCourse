package pro.komdosh.lab5

import pro.komdosh.attachSniffer
import pro.komdosh.createMainContainer
import pro.komdosh.lab5.agent.ReceiverAgent
import pro.komdosh.lab5.agent.SenderAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    val sender = cc.createNewAgent("Sender", SenderAgent::class.java.name, null)
    val receiver = cc.createNewAgent("Receiver", ReceiverAgent::class.java.name, null)

    attachSniffer(cc, listOf(sender, receiver))

    sender.start()
    receiver.start()
}
