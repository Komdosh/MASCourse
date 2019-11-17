package pro.komdosh.lab2

import createMainContainer
import pro.komdosh.lab2.agent.ExampleAgent

const val numberOfAgents = 2
fun main() {
    val cc = createMainContainer(withDummyAndSniffer = false, withGui = false)
    for (i in 0..numberOfAgents) {
        cc.createNewAgent("ExampleAgent #$i", ExampleAgent::class.java.name, null).start()
    }
}


