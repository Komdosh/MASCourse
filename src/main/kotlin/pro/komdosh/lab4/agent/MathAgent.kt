package pro.komdosh.lab4.agent

import jade.core.Agent
import pro.komdosh.lab4.behavior.MathBehaviour


class MathAgent : Agent() {
    override fun setup() {
        println("MathAgent Agent $aid is started")
        addBehaviour(MathBehaviour(this))
    }
}

