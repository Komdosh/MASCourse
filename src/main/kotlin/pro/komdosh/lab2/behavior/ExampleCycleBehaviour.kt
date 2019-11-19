package pro.komdosh.lab2.behavior

import jade.core.behaviours.CyclicBehaviour

class ExampleCycleBehaviour() : CyclicBehaviour() {
    override fun action() {
        println("${agent.localName} First Behaviour: now I block execution for 1 sec")
        block(1000)
        println("${agent.localName} First Behaviour: now I unlock execution, I will execute for infinite time, you better stop me")
    }
}
