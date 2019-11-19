package pro.komdosh.lab4.behavior.optimized

import jade.core.behaviours.SimpleBehaviour
import pro.komdosh.lab4.receiveMathResponse

class ReceiveMathActionBehaviour(
    private val requestId: String,
    private val position: Int,
    private val resultArray: DoubleArray
) : SimpleBehaviour() {

    private var finished = false
    override fun action() {
        val result = receiveMathResponse(agent, requestId)
        if (result != null) {
            resultArray[position] = result
            finished = true
        }
    }

    override fun done(): Boolean {
        return finished
    }
}




