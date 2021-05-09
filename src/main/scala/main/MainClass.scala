package main

import etl.SampleClass
import connect4._
import mcts.StatelessSolver

class MainClass extends Serializable {
}

object MainClass extends Serializable {
  val c4Obj = new Connect4Class()

  def main(args: Array[String]): Unit = {

    /*
    c4Obj.playAction(3, 1)
    c4Obj.playAction(3, 1)
    c4Obj.playAction(3, 1)
    c4Obj.playAction(3, 1)

    c4Obj.visualizeGrid()
     */

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

    val customConfig = Array(
      Array(1, 1, 0, 2, 0, 0, 0),
      Array(1, 2, 2, 1, 0, 0, 0),
      Array(2, 2, 2, 1, 1, 0, 0),
      Array(1, 1, 1, 2, 2, 0, 0),
      Array(1, 2, 1, 2, 1, 0, 0),
      Array(1, 2, 1, 2, 1, 0, 0)
    )
    // val blackStartConfig = Array.fill(height)(Array.fill(width)(0))
    // val c4State = new Connect4State(blackStartConfig)

    val controller = new Connect4Class(customConfig)
    // println(controller.checkVictory(2))


    val c4MDP = new Connect4MDP(controller)

    val statelessSolver = new StatelessSolver(
      c4MDP,
      800,
      1.4,
      1.0,
      true
    )
    statelessSolver.constructTree(3)



  }
}


