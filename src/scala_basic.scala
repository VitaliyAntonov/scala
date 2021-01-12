

//TODO Книга Scala Профессиональное программирование

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable
import scala.collection.immutable.HashSet
import scala.io.Source

object scala_basic extends App{
  println("Hello World")

  nabor // Использование наборов и отображений

}


//TODO Шаг 10. Использование наборов и отображений */
object nabor {

  //TODO  Листинг 3.2. Создание и инициализация массива
  val numNames = Array("zero", "one", "two")

  //TODO Листинг 3.3. Создание и инициализация списка
  val oneTwoThree = List(1, 2, 3)
  // Слияние двух списков с образованием нового списка при помощи ::: метода
  val oneTwo = List(1, 2)
  val threeFour = List(3, 4)
  val oneTwoThreeFour = oneTwo ::: threeFour
  println(oneTwo + " and " + threeFour + " were not mutated.")
  println("Thus, " + oneTwoThreeFour + " is a new list.")
  //оператор :: , который произносится cons («конс») добавляет в
  //начало существующего списка новый элемент и возвращает
  //получившийся в результате этого список.
  val twoThree = List(2, 3)
  val oneTwoThree_ok = 1 :: twoThree
  println("Метод :: " + oneTwoThree_ok)

  //TODO  Листинг 3.5. Создание, инициализация и использование неизменяемого набора */
  var jetSet = Set("Boeing", "Airbus")
  jetSet += "Lear"
  println(jetSet.contains("Cessna"))
  println(jetSet.contains("Airbus"))
  println(jetSet.contains("Lear"))

//TODO Листинг 3.6. Создание, инициализация и использование изменяемого набора */
  val movieSet = mutable.Set("Hitch", "Poltergeist")
  movieSet += "Shrek"
  println(movieSet)

//TODO Создание неизменяемого HashSet */
  val hashSet = HashSet("Tomatoes", "Chilies")
  println(hashSet + "Coriander")

  //TODO Листинг 3.7. Создание, инициализация и использование изменяемого отображения */
  val treasureMap = mutable.Map[Int, String]()
  treasureMap += (1 -> "Go to island.")
  treasureMap += (2 -> "Find big X on ground.")
  treasureMap += (3 -> "Dig.")
  println(treasureMap)
  println(treasureMap.mkString(" "))

  // TODO Кортежи
  val pair = (99, "Luftballons")
  println(pair._1)
  println(pair._2)

  val (pair1,pair2) =(40,"dfsdf")
  println(pair1)
  println(pair2)

//TODO  Листинг 3.9. Функция без побочных эффектов или var-переменных
  val datas = Array[String] ("leo","zayac","volk")
  def formatArgs(args: Array[String]) = args.mkString(" ") // Метод .mkString возвращает строку из элементов, разделённых указанной строкой
  println(formatArgs(datas))


  //TODO Листинг 3.10. Считывание строк из файла
  // import scala.io.Source
  val args = "/home/vitaly/develop/scalla/Text.txt"
  var line:String = null
  if (args.length > 0) {
    for (line <- Source.fromFile(args).getLines())
      println(line.length + " " + line)
  }
  else
    Console.err.println("Please enter filename")

  println(fun.readFileLines(args))

  lazy val busFile = fun.readFileLines(args)
  for(i <- 0 until busFile.length)
    println(i + " " + busFile(i))

//  line.fromFile(args).foreach {
//    print
}

object fun {
  def readFileLines(path: String): List[String] = { // чтение строк из файла в буфер
    var mab = List[String]()
    for (line <- Source.fromFile(path).getLines())  mab += line
    mab
  }
}



class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte): Unit = { sum += b }
  def checksum(): Int = ~(sum & 0xFF) + 1
}

//TODO Листинг 4.2. Объект-спутник для класса ChecksumAccumulator

object ChecksumAccumulator {
  private val cache = mutable.Map.empty[String, Int]
  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
}






