package pro.komdosh.lab7.agent

import jade.core.Agent
import jade.domain.DFService
import jade.domain.FIPAAgentManagement.DFAgentDescription
import jade.domain.FIPAAgentManagement.SearchConstraints
import jade.domain.FIPAAgentManagement.ServiceDescription

class DFSearchAgent : Agent() {
    override fun setup() {
        // Search for services of type " Course-Facilitator "
        println("Agent $localName searching for services of type \"Course-Facilitator\"")

        // Build the description used as template for the search
        val template = DFAgentDescription()
        val templateSd = ServiceDescription()
        templateSd.type = "Course-Facilitator"
        template.addServices(templateSd)

        val sc = SearchConstraints()
        // We want to receive 10 results at most
        sc.maxResults = 10

        val results = DFService.search(this, template, sc)
        if (results.isNotEmpty()) {
            println("Agent $localName found the following Course-Facilitator services:")
            results.forEach {
                val provider = it.name
                it.allServices.iterator().forEachRemaining { service ->
                    if (service is ServiceDescription && service.type == "Course-Facilitator") {
                        println("- Service \"${service.name}\" provided by agent ${provider.name}")
                    }
                }
            }
        } else {
            println("Agent $localName did not find any Course-Facilitator service")
        }
    }
}
