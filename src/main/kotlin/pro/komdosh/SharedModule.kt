package pro.komdosh

import jade.content.lang.sl.SLCodec
import jade.content.onto.basic.Action
import jade.core.*
import jade.domain.JADEAgentManagement.JADEManagementOntology
import jade.domain.JADEAgentManagement.SniffOn
import jade.lang.acl.ACLMessage
import jade.tools.DummyAgent.DummyAgent
import jade.tools.sniffer.Sniffer
import jade.wrapper.AgentContainer
import jade.wrapper.AgentController

fun createMainContainer(
    withGui: Boolean = true,
    withDummy: Boolean = true
): AgentContainer {
    val rt = Runtime.instance()
    val p = ProfileImpl()
    p.setParameter(Profile.MAIN_HOST, "localhost")
    p.setParameter(Profile.MAIN_PORT, "10098")
    p.setParameter(Profile.GUI, withGui.toString())
    val cc = rt.createMainContainer(p)
    if (withDummy) {
        cc.createNewAgent("DummyAgent", DummyAgent::class.java.name, null).start()
    }
    return cc
}

fun attachSniffer(mainContainer: AgentContainer, toSniffList: List<AgentController>) {
    mainContainer.createNewAgent("Sniffer", Sniffer::class.java.name, null).start()
    mainContainer.createNewAgent(
        "StartSnifferAgent",
        StartSnifferAgent::class.java.name,
        Array(1) { toSniffList }).start()

    Thread.sleep(3000) // time to render window (increase if you have slow computer)
}

class StartSnifferAgent : Agent() {
    override fun setup() {
        val toSniff = arguments[0] as List<AgentController>
        toSniff.forEach { launchSniffingSingleAgent(this, it) }
    }

    private fun launchSniffingSingleAgent(
        launcher: Agent,
        agentToSniffController: AgentController
    ) {
        val sniffer = AID("Sniffer", AID.ISLOCALNAME)
        val toSniff = AID(agentToSniffController.name, AID.ISGUID)
        val sniffOnAction = SniffOn()
        sniffOnAction.addSniffedAgents(toSniff)
        sniffOnAction.sniffer = sniffer

        val msg = ACLMessage(ACLMessage.REQUEST)
        msg.language = SLCodec().name
        msg.ontology = JADEManagementOntology.getInstance().name

        launcher.contentManager.registerLanguage(SLCodec())
        launcher.contentManager.registerOntology(JADEManagementOntology.getInstance())

        val action = Action(sniffer, sniffOnAction)
        launcher.contentManager.fillContent(msg, action)
        msg.addReceiver(sniffer)

        launcher.send(msg)
    }
}


private val actionsMap = mapOf<String, (Double, Double) -> Double>(
    Pair("add", Double::plus),
    Pair("subtract", Double::minus),
    Pair("multiply", Double::times),
    Pair("divide", Double::div)
)

fun getAvailableMathActions(): Set<String> {
    return actionsMap.keys
}

private fun getDouble(content: String, action: String, isSecond: Boolean = false): Double {
    var fromChar: Int = content.lastIndexOf(action) + action.length
    if (isSecond) {
        fromChar += content.substring(fromChar + 1).indexOf(" ")
    }
    val toChar: Int = content.substring(fromChar + 1).indexOf(if (isSecond) ")" else " ") + fromChar
    return content.substring(fromChar + 1, toChar + 1).toDouble()
}

fun doMath(agentName: String, content: String, operation: String): Double {
    val firstD = getDouble(content, operation)
    val secondD = getDouble(content, operation, true)

    val result = actionsMap[operation]?.let {
        it(firstD, secondD)
    } ?: 0.0

    println("$agentName Making: $firstD $operation $secondD = $result")

    return result
}
