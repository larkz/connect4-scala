package main

import etl.SampleClass
import connect4._
import mcts.{AdvancedStatelessSolver, StatelessSolver}

/*
class MainClass extends Serializable {
}

 */
object MainClass extends Serializable {
  val c4Obj = new Connect4Class()

  def main(args: Array[String]): Unit = {


    val height = 6
    val width = 7
    val blackStartConfig = Array.fill(height)(Array.fill(width)(0))

    val uberController = new Connect4Class(blackStartConfig)
    var i = 0

    var keepGoing = true

    while(keepGoing){
    // while (i < maxTurn){

      val nextAction = playAi(uberController.connect4Grid.map(_.clone), uberController.inducedPlayerTurn)

      uberController.playAction(nextAction)

      println("selected action -> next position")
      println(nextAction)
      uberController.visualizeGrid()
      if (uberController.checkVictory(1) || uberController.checkVictory(2) || uberController.connect4Grid.forall(_.forall(_ > 0))) {keepGoing = false}

      val nextPlayerMove = scala.io.StdIn.readInt()
      uberController.playAction(nextPlayerMove)
      uberController.visualizeGrid()


      if (uberController.checkVictory(1) || uberController.checkVictory(2) || uberController.connect4Grid.forall(_.forall(_ > 0))) {keepGoing = false}

      i += 1
    }

    // configurationTest()


  }

  def playAi(gridConfig: Array[Array[Int]], playerTurn: Int): Int = {

    val c4MDP = new Connect4MDP(new Connect4State(gridConfig, playerTurn))

    val solver = new AdvancedStatelessSolver(
      c4MDP,
      800,
      1.4,
      0.7,
      true
    )

    solver.constructTree(99)
    solver.getOptimalHorizon().toArray()(0).toString.toInt

  }


  def configurationTest(): Unit = {
    val customConfig = Array(
      Array(0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0),
      Array(1, 0, 2, 2, 1, 2, 0),
      Array(2, 0, 1, 2, 2, 1, 2),
      Array(1, 0, 1, 2, 1, 1, 1)
    )

    val height = 6
    val width = 7
    val blankStartConfig = Array.fill(height)(Array.fill(width)(0))

    val c4MDP = new Connect4MDP(new Connect4State(customConfig))

    val solver = new AdvancedStatelessSolver(
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


