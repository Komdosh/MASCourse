package pro.komdosh.lab4.behavior.optimized

import jade.core.behaviours.OneShotBehaviour
import pro.komdosh.lab4.sendMathRequest

class SendMathActionBehaviour(private val requestId: String, private val action: String) : OneShotBehaviour() {
    override fun action() {
        sendMathRequest(agent, requestId, action)
    }
}




