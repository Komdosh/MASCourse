package pro.komdosh.lab3.agent

import jade.core.Agent
import pro.komdosh.lab3.behavior.SenderBehaviour


class SenderAgent : Agent() {
    override fun setup() {
        println("Sender Agent $aid is started.")
        val senderBehaviour = SenderBehaviour()
        addBehaviour(senderBehaviour)
        if (senderBehaviour.finished) {
            removeBehaviour(senderBehaviour)
        }
    }
}

