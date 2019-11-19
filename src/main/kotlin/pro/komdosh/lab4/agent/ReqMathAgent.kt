package pro.komdosh.lab4.agent

import jade.core.Agent
import jade.core.behaviours.ParallelBehaviour
import pro.komdosh.lab4.behavior.SolveFirstBehaviour
import pro.komdosh.lab4.behavior.SolveSecondBehaviour


class ReqMathAgent : Agent() { //Third variant
    override fun setup() {
        println("ReqMathAgent Agent $aid is started")

        val parallelBehaviour = ParallelBehaviour()
        parallelBehaviour.addSubBehaviour(SolveFirstBehaviour())
        parallelBehaviour.addSubBehaviour(SolveSecondBehaviour())

        addBehaviour(parallelBehaviour)
    }
}

