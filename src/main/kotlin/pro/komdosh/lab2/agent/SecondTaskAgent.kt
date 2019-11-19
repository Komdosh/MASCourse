package pro.komdosh.lab2.agent

import jade.core.Agent
import jade.core.behaviours.ParallelBehaviour
import pro.komdosh.lab2.behavior.SecondTaskFirstSimpleBehaviour
import pro.komdosh.lab2.behavior.SecondTaskSecondSimpleBehaviour


class SecondTaskAgent : Agent() { //parallel behaviour with two simple behaviours
    override fun setup() {
        println("Agent $aid is started. Now Execute parallel behaviour")

        val parallelBehaviour = ParallelBehaviour()
        parallelBehaviour.addSubBehaviour(SecondTaskFirstSimpleBehaviour())
        parallelBehaviour.addSubBehaviour(SecondTaskSecondSimpleBehaviour())

        addBehaviour(parallelBehaviour)
    }
}

