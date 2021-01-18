

//TODO Книга Scala Профессиональное программирование

import useRational.{x, y}

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.mutable
import scala.collection.immutable.HashSet
import scala.io.Source


//TODO  Листинг 4.4. Использование трейта App для указания метода MAIN
object scala_basic extends App{
  println("Hello World")

  nabor // Использование наборов и отображений
  FallWinterSpringSummer
  FallWinterSpringSummer.use_updateRecordByName
  useRational // Использование созданного класса Rational
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

// TODO Создание неизменяемого HashSet */
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
  val args = "/home/oem/develop/scala/scala/Text.txt"
  var line:String = ""
  if (args.length > 0) {
    for (line <- Source.fromFile(args).getLines())
      println(line.length + " " + line)
  }
  else
    Console.err.println("Please enter filename")



//  println(fun.readFileLines(args))

  lazy val busFile = fun.readFileLines(args)
  for(i <- 0 until busFile.length)
    println(i + " " + busFile(i))

//  line.fromFile(args).foreach {
//    print
}

object fun {
  def readFileLines(path: String): List[String] = { // чтение строк из файла в буфер
    val mab = ListBuffer[String]()
    for (line <- Source.fromFile(path).getLines()) {
      mab += line
    }
    mab.toList
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



object FallWinterSpringSummer{
  println("FallWinterSpringSummer")
  for (season <- List("fall", "winter", "spring")) {
    println(season)
  }

  // TODO 5 Литералы
  // Целочисленные литералы
  val dec1 = 31 // dec1: Int = 31
  val prog = 0xCAFEBABEL // prog: Long = 3405691582
  val tower = 35L // tower: Long = 35
  val of = 31l  // of: Long = 31
  val little: Short = 367  // little: Short = 367
  val littler: Byte = 38   // littler: Byte = 38
  // Литералы чисел с плавающей точкой
  val big = 1.2345  //  big: Double = 1.2345
  val bigger = 1.2345e1 // bigger: Double = 12.345
  val biggerStill = 123E45  // biggerStill: Double = 1.23E47
  val littles = 1.2345F  //  littles: Float = 1.2345
  val littleBigger = 3e5f // littleBigger: Float = 300000.0
  val anotherDouble = 3e5 // anotherDouble: Double = 300000.0
  val yetAnother = 3e5D // yetAnother: Double = 300000.0
  // Символьные литералы
  val a = 'A' // a: Char = A
  val d = '\u0041' // d: Char = A
  val f = '\u0044' // f: Char = D
  println("B\u0041\u0044")// = 1 // BAD: Int = 1

  // TODO 5.2 Строковые литералы
  //  Строковые литералы
  val hello = "hello" // hello: String = hello
  val escapes = "\\\"\'" // escapes: String = \"'

  // TODO Три двойные кавычки и метод stripMargin для неформатированной строки - строка может содержать любые символы
  println("""|Welcome to Ultamix 3000.
             |Type "HELP" for help.""".stripMargin)

  // TODO Литералы обозначений == класс Symbol (scala.Symbol)
  def updateRecordByName(r: Symbol, value: Any) = {
    println("yes in updateRecordByName")
    if(r==Symbol("favoriteAlbum")){
      println(value)
      println(r.name) // для класса Symbol есть только метод .name
    }
  }
  def use_updateRecordByName: Unit ={
    updateRecordByName(Symbol("favoriteAlbum"), "OKComputer")
    println(Symbol("favoriteAlbum").name)
  }

  // Булевы литералы
  val bool = true // bool: Boolean = true
  val fool = false  // fool: Boolean = false

  // TODO 5.3. Строковая интерполяция
  def stringInterpolator {
    // интерполятор s
    val name = "reader"
    println(s"Hello, $name!") // в отношении всех результатов после знака $ вызывается метод toString
    println(s"The answer is ${6 * 7}.") // выражение следует заключить в фигурные скобки

    // интерполятор raw не распознает управляющие последовательности символьных литералов
    println(raw"No\\\\escape!") // выводит:  No\\\\escape!

    // интерполятор f позволяет прикреплять к встроенным выражениям
    // инструкции форматирования в стиле функции printf
    // используется синтаксис, заданный классом java.util.Formatter.
    println(f"${math.Pi}%.5f")  // res1: String = 3.14159
  }
}

// TODO 6 Функциональные объекты

// TODO 6.2. Конструкция класса Rational

//class Rational(val n: Int, val d: Int ){
//  require(d != 0) // Метод require создаст исключение IllegalArgumentException при false
//  override def toString: String = n + "/" + d
//  def add(that: Rational): Rational = new Rational(n * that.d + that.n * d, d * that.d)
//}

class Rational(n: Int, d: Int) {
  // проверка знаменателя на 0
  require(d != 0) // Метод require создаст исключение IllegalArgumentException при false
  val numer: Int = n
  val denom: Int = d

  def this(n: Int) = this(n, 1) // дополнительный конструктор

  override def toString = numer + "/" + denom
  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  def viewAdd(x: Rational) = {
    println(this + " + " + x + " = " + this.add(x))
  }
  def lessThan(that: Rational): Boolean = {
    this.numer * that.denom < that.numer * this.denom
  }
  def max(that: Rational): Rational =
    if (lessThan(that)) that else this
}

object useRational{
  val x = new Rational(1,2)
  val y = new Rational(2,3)
  x.viewAdd(y)
}





