package pro.komdosh.lab6

import pro.komdosh.createMainContainer
import pro.komdosh.lab6.agent.FIPAMathAgent
import pro.komdosh.lab6.agent.FIPAMathRequestAgent


const val mathAgentsCount = 3

fun main() {
    val cc = createMainContainer(withDummy = false, withGui = false)

/*    cc.createNewAgent(
        "FIPARequestInitiatorAgent",
        FIPARequestInitiatorAgent::class.java.name,
        arrayOf("FIPARequestResponderAgent")
    ).start()
    cc.createNewAgent("FIPARequestResponderAgent", FIPARequestResponderAgent::class.java.name, null).start()
    */

    for (i in 0 until mathAgentsCount) {
        cc.createNewAgent("FIPAMathAgent$i", FIPAMathAgent::class.java.name, null).start()
    }

    cc.createNewAgent(
        "FIPAMathRequestAgent", FIPAMathRequestAgent::class.java.name, arrayOf(
            "( multiply 659 412 )",
            "( divide 543 63 )",
            "multiply",
            "659 * 543 / 63 * 412"
        )
    ).start()

    cc.createNewAgent(
        "FIPAMathRequestAgentSecond", FIPAMathRequestAgent::class.java.name, arrayOf(
            "( multiply 43.65 5.6 )",
            "( multiply 54.6 56.4 )",
            "add",
            "43.65 * 5.6 + 54.6 * 56.4"
        )
    ).start()
}




