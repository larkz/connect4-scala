package connect4


// Representation
// 0: unfilled
// 1: Player 1
// 2: Player 2


class Connect4Class(val initialGrid: Array[Array[Int]] = Array.fill(6)(Array.fill(7)(0)))  {

  val width = initialGrid(0).length
  val height = initialGrid.length

  var connect4Grid = initialGrid.map(_.clone)
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
    println()
  }

  def playAction(action: Int, playerTurn: Int = inducedPlayerTurn): Unit = {
    if (legalActions.contains(action)) {
      // Start from bottom, go up
      for(i <- connect4Grid.indices.reverse){
        if (connect4Grid(i)(action) == 0){
          connect4Grid(i)(action) = playerTurn
          changeTurn()
          return
        }
      }
    }
  }

  def checkHorizontal(player: Int, grid: Array[Array[Int]] = connect4Grid): Boolean = {
    for (row <- grid){
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

  def checkVertical(player: Int, grid: Array[Array[Int]] = connect4Grid): Boolean = {
    for (col <- grid(0).indices){
      var count = 0
      for(b <- grid.indices){
        if (count == 0 && grid(b)(col) == player){
          count = 1
        } else if (b > 0 && grid(b)(col) == player && grid(b-1)(col) == player) {
          count += 1
          if (count >= 4) {return true}
        } else {
          count = 0
        }
      }
    }
    return false
  }

  def checkLeftDiagSingle(player: Int, x: Int, y: Int, grid: Array[Array[Int]] = connect4Grid): Boolean = {
    var xCount = x
    var yCount = y
    var count  = 0

    while (xCount < grid(0).length && yCount < grid.length){
      if (count == 0 && grid(yCount)(xCount) == player){
        count += 1
      } else if (xCount > x && yCount > y && grid(yCount)(xCount) == player && grid(yCount-1)(xCount-1) == player) {
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

  def checkRightDiagSingle(player: Int, x: Int, y: Int, grid: Array[Array[Int]] = connect4Grid): Boolean = {
    var xCount = x
    var yCount = y
    var count  = 0

    while (xCount >= 0 && yCount < grid.length){
      if (count == 0 && grid(yCount)(xCount) == player){
        count += 1
      } else if (xCount < x && yCount > y && grid(yCount)(xCount) == player && grid(yCount-1)(xCount+1) == player) {
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

  def checkAllDiagonals(player: Int, grid: Array[Array[Int]] = connect4Grid): Boolean = {
    for (x <- 0 to grid(0).length - 1 ){
      if (checkLeftDiagSingle(player, x, 0)) {return true}
      if (checkRightDiagSingle(player, x, 0)) {return true}
    }
    for (y <- 0 to grid.length - 1){
      if (checkLeftDiagSingle(player, 0, y)) {return true}
      if (checkRightDiagSingle(player, grid(0).length - 1, y)) {return true}
    }
    false
  }

  def checkVictory(player: Int, grid: Array[Array[Int]] = connect4Grid): Boolean = {
    return (checkHorizontal(player, grid) || checkVertical(player, grid)) || checkAllDiagonals(player, grid)
  }

  def resetBoard(): Unit = {
    connect4Grid = initialGrid.map(_.clone)
    inducedPlayerTurn = 1
  }

}

