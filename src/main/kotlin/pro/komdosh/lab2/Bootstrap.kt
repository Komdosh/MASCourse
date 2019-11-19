package pro.komdosh.lab2

import pro.komdosh.createMainContainer
import pro.komdosh.lab2.agent.SecondTaskAgent

const val numberOfAgents = 2
fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)
    for (i in 0 until numberOfAgents) {
//        cc.createNewAgent("ExampleAgent #$i", ExampleAgent::class.java.name, null).start()
        cc.createNewAgent("SecondTaskAgent #$i", SecondTaskAgent::class.java.name, null).start()
    }
}


