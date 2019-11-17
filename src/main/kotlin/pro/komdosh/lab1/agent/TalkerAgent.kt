package pro.komdosh.lab1.agent

import jade.core.Agent
import pro.komdosh.lab1.behavior.TalkerBehaviour


class TalkerAgent : Agent() {
    override fun setup() {
        println("Agent $aid is started")
        println("DummyAgent started automatically if you start your project from Bootstrap.kt")
        addBehaviour(TalkerBehaviour(this))
        println("Now age $aid is waiting for a message...")
    }
}

