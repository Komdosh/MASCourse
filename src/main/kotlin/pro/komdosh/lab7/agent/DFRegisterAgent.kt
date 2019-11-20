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

        if (localName != "DFRegister") {
            dfd.addServices(generateServiceDescription("Zhandarov", "Cpp"))
            dfd.addServices(generateServiceDescription("Panteleev", "Leap"))
            dfd.addServices(generateServiceDescription("Zhandarov", "Leap"))
        } else {
            dfd.addServices(generateServiceDescription("Zhandarov", "Leap"))
            dfd.addServices(generateServiceDescription("Panteleev", "Java"))
            dfd.addServices(generateServiceDescription("Panteleev", "Leap"))
            dfd.addServices(generateServiceDescription("Zhandarov", "Leap"))
        }

        DFService.register(this, dfd)
    }

    private fun generateServiceDescription(instructor: String, language: String): ServiceDescription {
        val serviceDescription = ServiceDescription()
        serviceDescription.name = "$localName-Course-Maker $language"
        serviceDescription.addOntologies("Course-Ontology")
        serviceDescription.addLanguages(language)
        serviceDescription.type = "Course-Maker"
        serviceDescription.addProperties(Property("courseName", "Multiagent system"))
        serviceDescription.addProperties(Property("courseInstructor", instructor))
        serviceDescription.addProperties(Property("dept", "CS"))
        return serviceDescription
    }

    override fun takeDown() {
        // Deregister from the yellow pages
        DFService.deregister(this)

        // Printout a dismissal message
        println("Course-Maker-agent ${aid.name} terminating.")
    }
}
