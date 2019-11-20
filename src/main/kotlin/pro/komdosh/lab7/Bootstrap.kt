package pro.komdosh.lab7

import pro.komdosh.createMainContainer
import pro.komdosh.lab7.agent.DFRegisterAgent
import pro.komdosh.lab7.agent.DFSearchAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = true)

    cc.createNewAgent("DFRegister", DFRegisterAgent::class.java.name, null).start()
    cc.createNewAgent("DFSearchAgent", DFSearchAgent::class.java.name, null).start()
}




