package pro.komdosh.lab1.agent

import jade.core.Agent
import pro.komdosh.lab1.behavior.SimpleTick
import java.util.concurrent.TimeUnit


class TickerAgent : Agent() {
    override fun setup() {
        println("Agent #${aid.localName}")
        addBehaviour(SimpleTick(this, TimeUnit.SECONDS.toMillis(1)))
    }
}
