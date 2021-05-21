package connect4

class Connect4State(gridInput: Array[Array[Int]], val playerTurn: Int = 1) {

  val grid = gridInput

  override def toString(): String = grid.map(_.map(_.toString).mkString(" ")).mkString("|")

}
