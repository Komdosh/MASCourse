package pro.komdosh.lab7.agent

import jade.core.Agent
import jade.domain.DFService
import jade.domain.FIPAAgentManagement.DFAgentDescription
import jade.domain.FIPAAgentManagement.Property
import jade.domain.FIPAAgentManagement.ServiceDescription

class DFRegisterAgent : Agent() {
    override fun setup() {
        println("Agent $localName registering service ")

        val dfd = DFAgentDescription()
        dfd.name = aid
        val sd = ServiceDescription()
        sd.name = "Agent1-Course-Facilitator"
        sd.type = "Course-Facilitator"
        sd.addProperties(Property("course_name", "Multiagent system"))
        dfd.addServices(sd)
        DFService.register(this, dfd)
    }

    override fun takeDown() {
        // Deregister from the yellow pages
        DFService.deregister(this)

        // Printout a dismissal message
        println("Course-Facilitator-agent ${aid.name} terminating.")
    }
}
