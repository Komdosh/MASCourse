package pro.komdosh.lab4

import jade.core.AID
import jade.core.Agent
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate


private fun getMathResponseMessageTemplate(replyWith: String): MessageTemplate {
    return MessageTemplate.and(
        MessageTemplate.MatchPerformative(ACLMessage.INFORM),
        MessageTemplate.and(
            MessageTemplate.MatchLanguage("predicate"),
            MessageTemplate.and(
                MessageTemplate.MatchOntology("math-ontology"),
                MessageTemplate.MatchReplyWith(replyWith)
            )
        )
    )
}

private fun parseAnswer(recvMsg: ACLMessage): Double {
    val fromChar = recvMsg.content.indexOf("(")
    val toChar = recvMsg.content.lastIndexOf(")")
    return recvMsg.content.substring(fromChar + 1, toChar - 1).toDouble()
}

fun sendMathRequest(agent: Agent, replyWith: String, content: String) {
    val msg = ACLMessage(ACLMessage.REQUEST)
    msg.addReceiver(AID("MathAgent", false))
    msg.language = "predicate"
    msg.ontology = "math-ontology"
    msg.replyWith = replyWith
    msg.content = content
    agent.send(msg)
    println("${agent.aid.localName} Sent message: ${msg.content} ReplyWith: ${msg.replyWith}")
}


fun receiveMathResponse(agent: Agent, replyWith: String): Double? {
    val mt = getMathResponseMessageTemplate(replyWith)

    val recvMsg = agent.blockingReceive(mt)
    if (recvMsg != null) {
        println("${agent.aid.localName} Message: ${recvMsg.content} was received ReplyWith: ${recvMsg.replyWith}")
        return parseAnswer(recvMsg)
    }
    return null
}
