package pro.komdosh.lab1.behavior

import jade.core.behaviours.TickerBehaviour
import pro.komdosh.lab1.agent.TickerAgent


class SimpleTick(private val agent: TickerAgent, period: Long) : TickerBehaviour(agent, period) {

    companion object {
        private const val MAX_STEPS = 5
    }

    private var currentStep: Int = 0

    init {
        this.setFixedPeriod(true)
    }

    override fun onTick() {
        if (currentStep < MAX_STEPS) {
            println("Agent " + this.agent.localName + ": tick=" + tickCount)
            this.currentStep++
        } else {
            this.stop()
        }
    }
}
