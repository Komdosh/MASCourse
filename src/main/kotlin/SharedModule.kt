import jade.core.Profile
import jade.core.ProfileImpl
import jade.core.Runtime
import jade.tools.DummyAgent.DummyAgent
import jade.tools.sniffer.Sniffer
import jade.wrapper.AgentContainer

fun createMainContainer(
    withGui: Boolean = true,
    withDummy: Boolean = true,
    withSniffer: Boolean = true
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
    if (withSniffer) {
        cc.createNewAgent("Sniffer", Sniffer::class.java.name, null).start()
    }
    return cc
}
