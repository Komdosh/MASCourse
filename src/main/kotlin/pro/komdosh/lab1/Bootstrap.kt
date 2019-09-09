package pro.komdosh.lab1

import jade.core.Profile
import jade.core.ProfileImpl
import jade.core.Runtime

private const val numberOfAgents = 5

fun main() {
    val rt = Runtime.instance()
    val p = ProfileImpl()
    p.setParameter(Profile.MAIN_HOST, "localhost")
    p.setParameter(Profile.MAIN_PORT, "10098")
    p.setParameter(Profile.GUI, "true")
    val cc = rt.createMainContainer(p)
    try {
        for (i in 1..numberOfAgents) {
            val agent = cc.createNewAgent(i.toString(), "pro.komdosh.lab1.DefaultAgent", null)
            agent.start()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}