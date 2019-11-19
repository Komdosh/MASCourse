package pro.komdosh.lab4.agent

import jade.core.Agent
import jade.core.behaviours.ParallelBehaviour
import pro.komdosh.lab4.behavior.MathBehaviour


const val numberOfMathExecutors = 4

class MathAgent : Agent() {
    override fun setup() {
        println("MathAgent Agent $aid is started")
        val parallelBehaviour = ParallelBehaviour()

        for (i in 0 until numberOfMathExecutors) {
            parallelBehaviour.addSubBehaviour(MathBehaviour())
        }

        addBehaviour(parallelBehaviour)
    }
}

