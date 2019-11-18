package pro.komdosh.lab2.agent

import jade.core.Agent
import jade.core.behaviours.ParallelBehaviour
import pro.komdosh.lab2.behavior.SecondTaskFirstSimpleBehaviour
import pro.komdosh.lab2.behavior.SecondTaskSecondSimpleBehaviour


class SecondTaskAgent : Agent() { //parallelBehaviour with two simple sub behaviours
    override fun setup() {
        println("Agent $aid is started. Now Execute parallel behaviour")

        val parallelBehaviour = ParallelBehaviour()
        parallelBehaviour.addSubBehaviour(SecondTaskFirstSimpleBehaviour(this))
        parallelBehaviour.addSubBehaviour(SecondTaskSecondSimpleBehaviour(this))

        addBehaviour(parallelBehaviour)
    }
}

