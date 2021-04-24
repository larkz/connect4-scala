package connect4

class Connect4Class(width: Int = 6, height: Int = 5)  {

  // Representation
  // 0: unfilled
  // 1: Player 1
  // 2: Player 2

  var connect4Grid = Array.fill(height)(Array.fill(width)(0))
  val legalActions = connect4Grid(0).indices.toList

  def getLegalActions(): List[Int] = {
    legalActions
  }

  def playAction(action: Int): Unit = {
    if (legalActions.contains(action)) {

    }
  }

}

