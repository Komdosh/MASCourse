package pro.komdosh.lab4.behavior

import jade.core.behaviours.SimpleBehaviour
import pro.komdosh.lab4.receiveMathResponse
import pro.komdosh.lab4.sendMathRequest

class SolveFirstBehaviour : SimpleBehaviour() {

    var finished: Boolean = false
    private var firstAns: Double = 0.toDouble()
    private var secondAns: Double = 0.toDouble()
    private var result: Double = 0.toDouble()
    private var state: Int = 0

    override fun action() {
        when (state) {
            0 -> {
                sendMathRequest(agent, "Request 1", "( multiply 659 412 )")
                state = 1
            }
            1 -> {
                val res = receiveMathResponse(agent, "Request 1")
                if (res != null) {
                    firstAns = res
                    state = 2
                }
            }
            2 -> {
                sendMathRequest(agent, "Request 2", "( divide 543 63 )")
                state = 3
            }
            3 -> {
                val res = receiveMathResponse(agent, "Request 2")
                if (res != null) {
                    secondAns = res
                    state = 4
                }
            }
            4 -> {
                sendMathRequest(agent, "Request 3", "( multiply $firstAns $secondAns )")
                state = 5
            }
            5 -> {
                val res = receiveMathResponse(agent, "Request 3")
                if (res != null) {
                    result = res
                    state = 6
                }
            }
        }
    }

    override fun done(): Boolean {
        if (state == 6) {
            println("${agent.aid.localName} 659 * 543 / 63 * 412 = $result")
            finished = true
        }
        return finished
    }
}




