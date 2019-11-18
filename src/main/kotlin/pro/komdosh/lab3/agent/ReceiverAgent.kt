package pro.komdosh.lab3.agent

import jade.core.Agent
import pro.komdosh.lab3.behavior.ReceiverBehaviour


class ReceiverAgent : Agent() {
    override fun setup() {
        println("Receiver Agent $aid is started")
        val receiverBehaviour = ReceiverBehaviour(this)
        addBehaviour(receiverBehaviour)
        if (receiverBehaviour.finished) {
            removeBehaviour(receiverBehaviour)
        }
    }
}

