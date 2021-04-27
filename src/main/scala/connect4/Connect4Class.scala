//package connect4


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
      var count = 0
      for(b <- row.indices){
        if (count == 0 && row(b) == player){
          count = 1
        } else if (b > 0 && row(b) == player && row(b-1) == player) {
          count += 1
          if (count >= 4) {return true}
        } else {
          count = 0
        }
      }
    }
    return false
  }

  def checkVertical(player: Int): Boolean = {
    for (col <- connect4Grid(0).indices){
      var count = 0
      for(b <- connect4Grid.indices){
        if (count == 0 && connect4Grid(b)(col) == player){
          count = 1
        } else if (b > 0 && connect4Grid(b)(col) == player && connect4Grid(b-1)(col) == player) {
          count += 1
          if (count >= 4) {return true}
        } else {
          count = 0
        }
      }
    }
    return false
  }

  def checkLeftDiagSingle(player: Int, x: Int, y: Int): Boolean = {
    var xCount = x
    var yCount = y
    var count  = 0

    while (xCount < connect4Grid(0).length && yCount < connect4Grid.length){
      if (count == 0 && connect4Grid(yCount)(xCount) == player){
        count += 1
      } else if (xCount > x && yCount > y && connect4Grid(yCount)(xCount) == player && connect4Grid(yCount-1)(xCount-1) == player) {
        count += 1
      } else {
        count = 0
      }
      if (count >= 4){
        return true
      }
      xCount += 1
      yCount += 1
    }
    false
  }

  def checkRightDiagSingle(player: Int, x: Int, y: Int): Boolean = {
    var xCount = x
    var yCount = y
    var count  = 0

    while (xCount >= 0 && yCount < connect4Grid.length){
      if (count == 0 && connect4Grid(yCount)(xCount) == player){
        count += 1
      } else if (xCount < x && yCount > y && connect4Grid(yCount)(xCount) == player && connect4Grid(yCount-1)(xCount+1) == player) {
        count += 1
      } else {
        count = 0
      }
      if (count >= 4){
        return true
      }
      xCount -= 1
      yCount += 1
    }
    false
  }

  def checkAllDiagonals(player: Int): Boolean = {
    for (x <- 0 to connect4Grid(0).length - 1 ){
      if (checkLeftDiagSingle(player, x, 0)) {return true}
      if (checkRightDiagSingle(player, x, 0)) {return true}
    }
    for (y <- 0 to connect4Grid.length - 1){
      if (checkLeftDiagSingle(player, 0, y)) {return true}
      if (checkRightDiagSingle(player, connect4Grid(0).length - 1, y)) {return true}
    }
    false
  }

  def checkVictory(player: Int): Boolean = {
    return (checkHorizontal(player) || checkVertical(player)) || checkAllDiagonals(player)
  }
}

