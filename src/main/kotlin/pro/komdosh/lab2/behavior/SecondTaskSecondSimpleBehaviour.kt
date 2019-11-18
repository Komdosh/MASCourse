package pro.komdosh.lab2.behavior

import jade.core.behaviours.SimpleBehaviour
import pro.komdosh.lab2.agent.SecondTaskAgent

class SecondTaskSecondSimpleBehaviour(
    private val agent: SecondTaskAgent,
    private var finished: Boolean = false,
    private var n: Int = 0
) : SimpleBehaviour() {

    override fun action() {
        block(2000)
        n++
        println("${agent.localName} Second Behaviour: I did this action $n time(s)")
    }

    override fun done(): Boolean {
        if (n > 7) {
            println("${agent.localName} Second Behaviour: I executed for $n times, I finished")
            finished = true
        }
        return finished
    }
}
