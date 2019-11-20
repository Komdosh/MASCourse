package pro.komdosh.lab6

import pro.komdosh.createMainContainer
import pro.komdosh.lab6.agent.FIPARequestInitiatorAgent
import pro.komdosh.lab6.agent.FIPARequestResponderAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    cc.createNewAgent(
        "FIPARequestInitiatorAgent",
        FIPARequestInitiatorAgent::class.java.name,
        arrayOf("FIPARequestResponderAgent")
    ).start()
    cc.createNewAgent("FIPARequestResponderAgent", FIPARequestResponderAgent::class.java.name, null).start()
}




