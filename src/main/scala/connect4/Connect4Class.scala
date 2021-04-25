package connect4


// Representation
// 0: unfilled
// 1: Player 1
// 2: Player 2


class Connect4Class(width: Int = 6, height: Int = 5)  {

  var connect4Grid = Array.fill(height)(Array.fill(width)(0))
  val legalActions = connect4Grid(0).indices.toList
  var inducedPlayerTurn = 1

  def getLegalActions(): List[Int] = {
    legalActions
  }

  def changeTurn(): Unit = {
    if (inducedPlayerTurn == 1) inducedPlayerTurn = 2
    else inducedPlayerTurn = 1
  }

  def visualizeGrid(): Unit = {
    print(connect4Grid.map(_.mkString("|")).mkString("\n"))
  }

  def playAction(action: Int, playerTurn: Int = inducedPlayerTurn): Unit = {
    if (legalActions.contains(action)) {
      // Start from bottom, go up
      for(i <- connect4Grid.indices.reverse){
        if (connect4Grid(i)(action) == 0){
          connect4Grid(i)(action) = playerTurn
          changeTurn()
          visualizeGrid()
          return
        }
      }
    }
  }

  def checkHorizontal(player: Int): Boolean = {
    for (row <- connect4Grid){
      println(row.mkString("|"))
      var count = 0
      for(b <- row.indices){
        print(row(b))
        if (count == 0 && row(b) == player){
          count = 1
          println("---A")
        } else if (b > 0 && row(b) == player && row(b-1) == player) {
          count += 1
          println("---B")
          if (count >= 4) {return true}
        } else {
          count = 0
          println("---C")
        }
      }

    }
    return false
  }

  def checkVictory(player: Int): Boolean = {
    return false
  }
}

