package pro.komdosh.lab1

import jade.core.Agent
import java.util.concurrent.TimeUnit


class DefaultAgent : Agent() {
    private val linkedAgents: Array<String>? = null
    private val number: Float = 0.toFloat()
    override fun setup() {
        val id = Integer.parseInt(aid.localName)
        println("Agent #$id")
        addBehaviour(SimpleTick(this, TimeUnit.SECONDS.toMillis(1)))
    }
}