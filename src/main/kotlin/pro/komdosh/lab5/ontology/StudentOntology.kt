package pro.komdosh.lab5.ontology

import jade.content.onto.BasicOntology
import jade.content.onto.Ontology
import jade.content.schema.AgentActionSchema
import jade.content.schema.ConceptSchema
import jade.content.schema.PredicateSchema
import jade.content.schema.PrimitiveSchema


class StudentOntology private constructor(base: Ontology) : Ontology(NAME, base) {
    init {
        add(ConceptSchema(STUDENT), Student::class.java)
        add(ConceptSchema(INSTRUCTOR), Instructor::class.java)
        add(ConceptSchema(COURSE), Course::class.java)
        add(AgentActionSchema(REGISTER), Register::class.java)
        add(AgentActionSchema(UNREGISTER), Unregister::class.java)
        add(PredicateSchema(REGISTERED), Registered::class.java)
        add(PredicateSchema(UNREGISTERED), Unregistered::class.java)
        add(PredicateSchema(COURSE_AVAILABLE), CourseAvailable::class.java)

        var cs = getSchema(STUDENT) as ConceptSchema
        cs.add("name", getSchema(BasicOntology.STRING) as PrimitiveSchema)
        cs.add("groupnumber", getSchema(BasicOntology.STRING) as PrimitiveSchema)

        cs = getSchema(INSTRUCTOR) as ConceptSchema
        cs.add("name", getSchema(BasicOntology.STRING) as PrimitiveSchema)
        cs.add("dept", getSchema(BasicOntology.STRING) as PrimitiveSchema)

        cs = getSchema(COURSE) as ConceptSchema
        cs.add("name", getSchema(BasicOntology.STRING) as PrimitiveSchema)
        cs.add("instructor", getSchema(INSTRUCTOR) as ConceptSchema)

        var agentActionSchema = getSchema(REGISTER) as AgentActionSchema
        agentActionSchema.add("student", getSchema(STUDENT) as ConceptSchema)
        agentActionSchema.add("course", getSchema(COURSE) as ConceptSchema)

        agentActionSchema = getSchema(UNREGISTER) as AgentActionSchema
        agentActionSchema.add("student", getSchema(STUDENT) as ConceptSchema)
        agentActionSchema.add("course", getSchema(COURSE) as ConceptSchema)

        var ps = getSchema(REGISTERED) as PredicateSchema
        ps.add("student", getSchema(STUDENT) as ConceptSchema)
        ps.add("course", getSchema(COURSE) as ConceptSchema)

        ps = getSchema(UNREGISTERED) as PredicateSchema
        ps.add("student", getSchema(STUDENT) as ConceptSchema)
        ps.add("course", getSchema(COURSE) as ConceptSchema)

        ps = getSchema(COURSE_AVAILABLE) as PredicateSchema
        ps.add("course", getSchema(COURSE) as ConceptSchema)
    }

    companion object {
        /**A symbolic constant, containing the name of this ontology. */
        const val NAME = "student-ontology"
        // VOCABULARY
        // Concepts
        const val STUDENT = "STUDENT"
        const val INSTRUCTOR = "INSTRUCTOR"
        const val COURSE = "COURSE"
        // Actions
        const val REGISTER = "REGISTER"
        const val UNREGISTER = "UNREGISTER"
        // Predicates
        const val REGISTERED = "REGISTERED"
        const val UNREGISTERED = "UNREGISTERED"
        const val COURSE_AVAILABLE = "COURSE-AVAILABLE"
        /**
         * This method grants access to the unique instance of the
         * employment ontology.
         * @return An `Ontology` object, containing the concepts
         * of the employment ontology.
         */
        val instance: Ontology = StudentOntology(BasicOntology.getInstance())
    }
}
