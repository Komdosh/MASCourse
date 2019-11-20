package pro.komdosh.lab7.agent

import jade.core.AID
import jade.core.Agent
import jade.domain.DFService
import jade.domain.FIPAAgentManagement.DFAgentDescription
import jade.domain.FIPAAgentManagement.Property
import jade.domain.FIPAAgentManagement.SearchConstraints
import jade.domain.FIPAAgentManagement.ServiceDescription

class DFSearchAgent : Agent() {
    override fun setup() {

        val instructor = "Zhandarov"
        val language = "Leap"
        println("Agent $localName searching for services with instructor: $instructor and language $language ")

        val sc = SearchConstraints()
        sc.maxResults = 2

        val results = DFService.search(this, generateSearchTemplate(instructor, language), sc)
        if (results.isNotEmpty()) {
            println("Agent $localName found the following services:")
            results.forEach {
                printServicesInfo(it, language, instructor)
            }
        } else {
            println("Agent $localName did not found any service with following request")
        }
    }

    private fun printServicesInfo(
        it: DFAgentDescription,
        language: String,
        instructor: String
    ) {
        val provider = it.name
        it.allServices.asSequence().filter { service -> service is ServiceDescription }.forEach { service ->
            if (service is ServiceDescription) {
                val languageCheck = service.allLanguages.asSequence().any { lang -> lang == language }
                val propertyCheck = service.allProperties.asSequence().any { prop ->
                    if (prop is Property) {
                        prop.name == "courseInstructor" && prop.value == instructor
                    } else false
                }
                if (languageCheck && propertyCheck) {
                    printServiceDescription(service, provider)
                }
            }
        }
    }

    private fun printServiceDescription(
        service: ServiceDescription,
        provider: AID
    ) {
        println("- Service \"${service.name}\" provided by agent ${provider.name}")
        println("  Full Description:")
        println("   name: ${service.name}")
        service.allLanguages.forEach { language ->
            println("   language: $language")
        }
        service.allProperties.forEach { property ->
            if (property is Property) {
                println("   ${property.name}: ${property.value}")
            }
        }
    }

    private fun generateSearchTemplate(instructor: String, language: String): DFAgentDescription {
        val template = DFAgentDescription()
        val templateSd = ServiceDescription()
        templateSd.addProperties(Property("courseInstructor", instructor))
        templateSd.addLanguages(language)
        template.addServices(templateSd)
        return template
    }
}
