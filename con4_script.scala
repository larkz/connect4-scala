val c4Obj = new Connect4Class()

c4Obj.playAction(1, 1)
c4Obj.playAction(2, 1)
c4Obj.playAction(3, 1)
c4Obj.playAction(4, 1)

c4Obj.visualizeGrid()


c4Obj.checkHorizontal(1)



print(c4Obj.connect4Grid.map(_.mkString("|")).mkString("\n"))

