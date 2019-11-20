package pro.komdosh.lab6.behaviour

import jade.core.behaviours.SimpleBehaviour
import pro.komdosh.lab6.agent.FIPAMathRequestAgent

class ResultBehaviour(
    private val agent: FIPAMathRequestAgent,
    private val results: Array<Double>,
    private val lastActionName: String,
    private val toEval: String
) : SimpleBehaviour() {

    var finished: Boolean = false
    override fun action() {
        if (results.any { it == Double.MIN_VALUE }) {
            return
        }

        val resultAction = "( $lastActionName ${results[0]} ${results[1]} )"
        val msg = agent.sendAction(resultAction)
        agent.addBehaviour(FIPAMathResultRequestBehaviour(agent, msg, toEval))

        finished = true
    }

    override fun done(): Boolean {
        return finished
    }

}




