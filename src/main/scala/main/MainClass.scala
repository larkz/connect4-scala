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
    val height = 7
    val width = 6
    val c4State = new Connect4State(Array.fill(height)(Array.fill(width)(0)))
    val c4MDP = new Connect4MDP(c4State, true)

    val stateless = new StatelessSolver(
      c4MDP,
      800,
      1.4,
      0.9,
      true
    )
    stateless.constructTree(50)

  }
}


