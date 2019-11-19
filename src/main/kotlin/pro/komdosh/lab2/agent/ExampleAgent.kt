package pro.komdosh.lab2.agent

import jade.core.Agent
import pro.komdosh.lab2.behavior.ExampleCycleBehaviour
import pro.komdosh.lab2.behavior.ExampleSimpleBehaviour


class ExampleAgent : Agent() {
    override fun setup() {
        println("Agent $aid is started.")
        addBehaviour(ExampleCycleBehaviour())
        addBehaviour(ExampleSimpleBehaviour())
    }
}

