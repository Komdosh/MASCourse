package pro.komdosh.lab2.behavior

import jade.core.behaviours.SimpleBehaviour

class ExampleSimpleSubBehaviour(private var finished: Boolean = false) : SimpleBehaviour() {
    override fun action() {
        println("${agent.localName} Third Sub Behaviour: now I block execution for 3 sec")
        block(3000)
        println("${agent.localName} Third Sub Behaviour: now I unlock execution")
    }

    override fun done(): Boolean {
        println("${agent.localName} Third Sub Behaviour: I did one execution, so I'm done")
        finished = true
        return finished
    }
}
