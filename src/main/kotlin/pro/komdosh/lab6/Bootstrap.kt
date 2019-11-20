package pro.komdosh.lab6

import pro.komdosh.createMainContainer
import pro.komdosh.lab4.agent.MathAgent
import pro.komdosh.lab4.agent.OptimizedReqMathAgent


fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

    cc.createNewAgent("MathAgent", MathAgent::class.java.name, null).start()
//    cc.createNewAgent("ReqMathAgent", ReqMathAgent::class.java.name, null).start()
    cc.createNewAgent("OptimizedReqMathAgent", OptimizedReqMathAgent::class.java.name, null).start()
}




