package pro.komdosh.lab1

import createMainContainer
import pro.komdosh.lab1.agent.TalkerAgent
import pro.komdosh.lab1.agent.TickerAgent


private const val numberOfTickerAgents = 5

fun main() {
    val cc = createMainContainer()

    cc.createNewAgent("TalkerAgent",  TalkerAgent::class.java.name, null).start()

    for (i in 1..numberOfTickerAgents) {
        val agentController = cc.createNewAgent("Ticker #$i", TickerAgent::class.java.name, null)
        agentController.start()
    }
}
