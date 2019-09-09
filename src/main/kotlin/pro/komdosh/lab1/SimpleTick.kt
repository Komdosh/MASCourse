package pro.komdosh.lab1

import jade.core.behaviours.TickerBehaviour

private const val MAX_STEPS = 10

class SimpleTick(agent: DefaultAgent, period: Long) : TickerBehaviour(agent, period) {

    private var currentStep: Int

    init {
        this.setFixedPeriod(true)
        this.agent = agent
        this.currentStep = 0
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