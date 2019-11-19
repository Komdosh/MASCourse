package pro.komdosh.lab4

import pro.komdosh.createMainContainer
import pro.komdosh.lab4.agent.MathAgent
import pro.komdosh.lab4.agent.ReqMathAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    cc.createNewAgent("MathAgent", MathAgent::class.java.name, null).start()
    cc.createNewAgent("ReqMathAgent", ReqMathAgent::class.java.name, null).start()
}




