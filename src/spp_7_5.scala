


object useCase{
  // TODO Листинг 7.14. Выражение сопоставления с побочными эффектами
  println("========= Use match ... case  ==========")
  val args: String = "salt"
  val firstArg: String = if (args.nonEmpty) args else ""
  firstArg match {
    case "salt" => println("pepper")
    case "chips" => println("salsa")
    case "eggs" => println("bacon")
    case _ => println("huh?")
  }

  // TODO Вывод таблицы умноженния
  println("======  Таблица умножения  ======")
  var i = 1
  while(i <= 10){
    var k = 1
    while(k <=10){
      val rez = i*k
      print(" " + rez)
      var l =1
      while(l <= (4 - rez.toString.length) ){
        print(" ")
        l += 1
      }
      k += 1
    }
    i += 1
    println("")
  }

  // TODO Листинг 7.19. Функциональный способ создания таблицы умножения
  // Возвращение строчки в виде последовательности
  def makeRowSeq(row: Int): IndexedSeq[String] =
    for (col <- 1 to 10) yield {
      val prod: String = (row * col).toString
      val padding: String = " " * (4 - prod.length)
      padding + prod
    }
  // Возвращение строки в виде строкового значения
  def makeRow(row: Int): String = makeRowSeq(row).mkString
  // Возвращение таблицы в виде строковых значений, по одному значению
  // на каждую строку
  def multiTable(): String = {
    val tableSeq = // последовательность из строк таблицы
    for (row <- 1 to 10)
      yield makeRow(row)
    tableSeq.mkString("\n")
  }

}

// TODO Листинг 8.1. LongLines с закрытым методом processLine
/**
 * показаны два метода, которые вместе считывают данные из файла с
 * заданным именем и выводят строки, длина которых превышает заданную
 */
import scala.io.Source
object LongLines {
  def processFile(filename: String, width: Int): Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
  private def processLine(filename: String, width: Int, line: String): Unit = {
    if (line.length > width)
      println(filename + ": " + line.trim)
  }
}

// TODO Листинг 8.2. LongLines с локальной функцией processLine
import scala.io.Source
object LongLines1 {
  def processFile(filename: String, width: Int): Unit =
  {
    def processLine(line: String): Unit = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}


