package pro.komdosh.lab4.agent

import jade.core.Agent
import jade.core.behaviours.ParallelBehaviour
import jade.core.behaviours.SequentialBehaviour
import pro.komdosh.lab4.behavior.optimized.ReceiveMathActionBehaviour
import pro.komdosh.lab4.behavior.optimized.ResultBehaviour
import pro.komdosh.lab4.behavior.optimized.SendMathActionBehaviour
import pro.komdosh.lab4.behavior.optimized.SendReduceMathActionBehaviour


class OptimizedReqMathAgent : Agent() { //Third variant
    private val firstResult = doubleArrayOf(Double.MIN_VALUE, Double.MIN_VALUE)
    private val secondResult = doubleArrayOf(Double.MIN_VALUE, Double.MIN_VALUE)

    override fun setup() {
        println("OptimizedReqMathAgent Agent $aid is started")

        addBehavioursForFirstTask()
        addBehavioursForSecondTask()
    }

    private fun addBehavioursForFirstTask() {
        val firstBehaviour = SequentialBehaviour()
        val secondBehaviour = SequentialBehaviour()

        firstBehaviour.addSubBehaviour(SendMathActionBehaviour("Request 51", "( multiply 659 412 )"))
        firstBehaviour.addSubBehaviour(ReceiveMathActionBehaviour("Request 51", 0, firstResult))

        secondBehaviour.addSubBehaviour(SendMathActionBehaviour("Request 52", "( divide 543 63 )"))
        secondBehaviour.addSubBehaviour(ReceiveMathActionBehaviour("Request 52", 1, firstResult))


        val parallelBehaviour = ParallelBehaviour()
        parallelBehaviour.addSubBehaviour(firstBehaviour)
        parallelBehaviour.addSubBehaviour(secondBehaviour)

        val reduce = SequentialBehaviour()
        reduce.addSubBehaviour(parallelBehaviour)
        reduce.addSubBehaviour(SendReduceMathActionBehaviour("Result 50", "multiply", firstResult))
        reduce.addSubBehaviour(ResultBehaviour("659 * 543 / 63 * 412", "Result 50"))

        addBehaviour(reduce)
    }

    private fun addBehavioursForSecondTask() {
        val firstBehaviour = SequentialBehaviour()
        val secondBehaviour = SequentialBehaviour()

        firstBehaviour.addSubBehaviour(SendMathActionBehaviour("Request 61", "( multiply 43.65 5.6 )"))
        firstBehaviour.addSubBehaviour(ReceiveMathActionBehaviour("Request 61", 0, secondResult))

        secondBehaviour.addSubBehaviour(SendMathActionBehaviour("Request 62", "( multiply 54.6 56.4 )"))
        secondBehaviour.addSubBehaviour(ReceiveMathActionBehaviour("Request 62", 1, secondResult))


        val parallelBehaviour = ParallelBehaviour()
        parallelBehaviour.addSubBehaviour(firstBehaviour)
        parallelBehaviour.addSubBehaviour(secondBehaviour)

        val reduce = SequentialBehaviour()
        reduce.addSubBehaviour(parallelBehaviour)
        reduce.addSubBehaviour(SendReduceMathActionBehaviour("Result 60", "add", secondResult))
        reduce.addSubBehaviour(ResultBehaviour("43.65 * 5.6 + 54.6 * 56.4", "Result 60"))

        addBehaviour(reduce)
    }
}

