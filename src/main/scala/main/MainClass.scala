package main

import etl.SampleClass

class MainClass extends Serializable {
}

object MainClass extends Serializable {

  def main(args: Array[String]): Unit = {

    println("Feature Ingest Main Routine: ")
    val dummy = args(0)
    dummy match {
      case "sampleclass" => sampleMainMethod(args(1))
      case _ => throw new ClassNotFoundException(s"$dummy entry key does not exist !")
    }
  }

  def sampleMainMethod(arg1: String): String = {
    val sClass = new SampleClass()
    sClass.sampleMethod(arg1)
  }
}


