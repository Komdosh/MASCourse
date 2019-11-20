package pro.komdosh.lab5

import pro.komdosh.createMainContainer
import pro.komdosh.lab5.agent.ReceiverAgent
import pro.komdosh.lab5.agent.SenderAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    cc.createNewAgent("Sender", SenderAgent::class.java.name, null).start()
    cc.createNewAgent("Receiver", ReceiverAgent::class.java.name, null).start()
}
