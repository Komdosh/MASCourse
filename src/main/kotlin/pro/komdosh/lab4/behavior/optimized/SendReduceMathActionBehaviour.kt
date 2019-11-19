package pro.komdosh.lab4.behavior.optimized

import jade.core.behaviours.OneShotBehaviour
import pro.komdosh.lab4.sendMathRequest

class SendReduceMathActionBehaviour(
    private val resultRequest: String,
    private val action: String,
    private val resultArray: DoubleArray
) : OneShotBehaviour() {
    override fun action() {
        while (resultArray.any { it == Double.MIN_VALUE });
        sendMathRequest(agent, resultRequest, "( $action ${resultArray[0]} ${resultArray[1]} )")
    }
}




