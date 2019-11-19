package pro.komdosh.lab4.behavior.optimized

import jade.core.behaviours.OneShotBehaviour
import pro.komdosh.lab4.receiveMathResponse

class ResultBehaviour(
    private val toEvalFunction: String,
    private val resultRequestId: String
) : OneShotBehaviour() {

    override fun action() {
        val result = receiveMathResponse(agent, resultRequestId)
        print("${agent.aid.localName} Result for: $toEvalFunction = $result")
    }

}




