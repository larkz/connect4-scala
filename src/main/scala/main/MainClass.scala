package main

import etl.SampleClass
import connect4._
import mcts.{AdvancedStatelessSolver, StatelessSolver}

class MainClass extends Serializable {
}

object MainClass extends Serializable {
  val c4Obj = new Connect4Class()

  def main(args: Array[String]): Unit = {

    configurationTest()


    /*
    c4Obj.connect4Grid = Array(
      Array(0, 0, 0, 2, 0, 0, 0),
      Array(1, 2, 2, 1, 0, 0, 0),
      Array(2, 2, 2, 1, 0, 0, 0),
      Array(1, 1, 1, 2, 0, 0, 0),
      Array(1, 2, 1, 2, 2, 0, 0),
      Array(1, 2, 1, 2, 1, 0, 0)
    )
    c4Obj.visualizeGrid()
    println(c4Obj.checkVictory(2))
    */

    /*
    c4Obj.playAction(3, 1)
    c4Obj.playAction(3, 1)
    c4Obj.playAction(3, 1)
    c4Obj.playAction(3, 1)

    c4Obj.visualizeGrid()
     */

    // State tester

    /*
    val sampleConfig = Array(
      Array(0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 2, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0),
      Array(0, 1, 0, 1, 2, 0, 0),
      Array(0, 1, 1, 2, 2, 0, 2),
      Array(2, 1, 2, 1, 1, 1, 2)
    )
    val sampleController = new Connect4Class(sampleConfig)

    println(sampleController.checkVictory(1))
    println(sampleController.checkVictory(2))
    println(sampleController.checkAllDiagonals(1))

    println(sampleController.connect4Grid.forall(_.forall(_ > 0)))

     */

  }



  def configurationTest(): Unit = {
    val customConfig = Array(
      Array(1, 1, 0, 0, 0, 0, 0),
      Array(1, 2, 2, 0, 0, 0, 0),
      Array(2, 2, 2, 1, 0, 0, 0),
      Array(1, 1, 1, 2, 2, 1, 0),
      Array(1, 2, 1, 2, 1, 1, 1),
      Array(1, 2, 1, 2, 1, 2, 2)
    )

    val height = 6
    val width = 7
    val blackStartConfig = Array.fill(height)(Array.fill(width)(0))

    val c4MDP = new Connect4MDP(new Connect4State(customConfig))

    val solver = new Connect4Solver(
      c4MDP,
      800,
      1.4,
      1.0,
      true
    )
    solver.constructTree(300)
    println(solver.getOptimalHorizon())

    // println(solver)
  }

}


