package pro.komdosh.lab2.behavior

import jade.core.behaviours.SimpleBehaviour

class ExampleSimpleBehaviour(
    private var finished: Boolean = false,
    private var n: Int = 0
) : SimpleBehaviour() {

    override fun action() {
        println("${agent.localName} Second Behaviour: now I block execution for 3 sec")
        block(3000)
        println("${agent.localName} Second Behaviour: now I unlock execution")
        n++
        println("${agent.localName} Second Behaviour: I did this action $n time(s)")
        if (n == 2) {
            println("${agent.localName} Second Behaviour: now I will add sub behaviour")
            agent.addBehaviour(ExampleSimpleSubBehaviour())
        }
    }

    override fun done(): Boolean {
        if (n > 3) {
            println("${agent.localName} Second Behaviour: I executed for $n times, I finished")
            finished = true
        }
        return finished
    }
}
