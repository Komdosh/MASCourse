package pro.komdosh.lab2.behavior

import jade.core.behaviours.SimpleBehaviour

class SecondTaskFirstSimpleBehaviour(
    private var finished: Boolean = false,
    private var n: Int = 0
) : SimpleBehaviour() {

    override fun action() {
        block(5000)
        n++
        println("${agent.localName} First Behaviour: I did this action $n time(s)")
    }

    override fun done(): Boolean {
        if (n > 4) {
            println("${agent.localName} First Behaviour: I executed for $n times, I finished")
            finished = true
        }
        return finished
    }
}
