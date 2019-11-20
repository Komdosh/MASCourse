package pro.komdosh.lab7

import pro.komdosh.createMainContainer
import pro.komdosh.lab7.agent.DFRegisterAgent
import pro.komdosh.lab7.agent.DFSearchAgent
import java.lang.Thread.sleep


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    cc.createNewAgent("DFRegister", DFRegisterAgent::class.java.name, null).start()
    cc.createNewAgent("DFRegister1", DFRegisterAgent::class.java.name, null).start()
    sleep(1000)
    cc.createNewAgent("DFSearchAgent", DFSearchAgent::class.java.name, null).start()
}




