package pro.komdosh.lab1

import jade.core.Profile
import jade.core.ProfileImpl
import jade.core.Runtime
import jade.tools.DummyAgent.DummyAgent
import jade.tools.sniffer.Sniffer
import pro.komdosh.lab1.agent.TalkerAgent
import pro.komdosh.lab1.agent.TickerAgent


private const val numberOfAgents = 5

fun main() {
    val rt = Runtime.instance()
    val p = ProfileImpl()
    p.setParameter(Profile.MAIN_HOST, "localhost")
    p.setParameter(Profile.MAIN_PORT, "10098")
    p.setParameter(Profile.GUI, "true")
    val cc = rt.createMainContainer(p)

    cc.createNewAgent("Sniffer", Sniffer::class.java.name, null).start()
    cc.createNewAgent("DummyAgent", DummyAgent::class.java.name, null).start()

    cc.createNewAgent("TalkerAgent",  TalkerAgent::class.java.name, null).start()

    for (i in 1..numberOfAgents) {
        val agentController = cc.createNewAgent("Ticker #$i", TickerAgent::class.java.name, null)
        agentController.start()
    }
}
