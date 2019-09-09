import jade.core.Agent

class HelloWorld : Agent() {
    override fun setup() {
        println("Hello World")
        println("My agent name is ${aid.localName}")
    }
}