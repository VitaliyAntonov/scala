
import FileMatcher.filesEnding

import java.io.File


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
import scala.Console.out
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
    println("===========  Локальные функции  ============")
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}

// TODO Функциональные литералы
object functionalLiteral{

//    x: Int => x + 1  // Простой функциональный литерал, прибавляющий к числу единицу
  var increase = (x: Int) => x + 1
  println("==========  Функциональные литералы  ===========")
  println(increase(10))
  increase = (x: Int) => x + 9999
  println(increase(10))
  increase = (x: Int) => {
    print("We ")
    print("are ")
    print("here! ")
    x + 1
  }
  println(increase(10))

  val toDesktop = (x: Any) => print(x+"  ")

  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  println("=====  foreach1  ======")
  someNumbers.foreach((x: Int) => println(x)) // метод foreach  получает функцию в качестве аргумента
                                              // и вызывает ее в отношении каждого элемента своей коллекции
  println("=====  foreach2  ======")
  someNumbers.foreach(toDesktop)  // Использование функционального литерала
  println("\n=====  filter  ======")
  someNumbers.filter(x => x > 0).map(toDesktop) // метод filter  выбирает те элементы коллекции,
                                        // которые проходят выполняемую пользователем проверку
// TODO 8.4. Краткие формы функциональных литералов
  someNumbers.foreach(x => println(x))  // Для сокращения записи отбрасываем тип параметра
                                        // и убираем скобки вокруг параметра
  // TODO 8.5. Синтаксис заместителя
  /**
   *  Знак подчёркивания как заместитель параметра
   */
  println("======== Заместитель пераметра val f = (_: Int) + (_: Int) ============= ")
  someNumbers.filter(_ > 0).map(println(_))
  val f = (_: Int) + (_: Int)
  println(f(10,5))

  // TODO 8.6. Частично применяемые функции
  println("========  8.6. Частично применяемые функции  ==============")
  def sum(a: Int, b: Int, c: Int): Int = a + b + c
  println("sum(1,2,3) = " + sum(1,2,3))
  val a = sum _
  println("a(1,2,3) = " + a(1,2,3)) //  a(1, 2, 3) является краткой формой кода a.apply(1, 2, 3)

  val b = sum(1, _: Int, 3)
  println("b(2) = " + b(2))

  // TODO 8.7 Замыкания
  println("==========  8.7 Замыкания  ==========")
  var more = 1
  val addMore = (x: Int) => x + more  // функциональный литерал будет нормально работать,
                                      // пока доступно что-нибудь с именем more
                                      // ссылка на захваченную переменную more, называется замыканием
  more = 9999
  println(addMore(10))

  var sums = 0
  someNumbers.foreach(sums += _)
  println(sums)

  def makeIncreaser(more: Int) = (x: Int) => x + more
  val inc1 = makeIncreaser(1)
  println("inc1(10) = " + inc1(10))
  val inc9999 = makeIncreaser(9999)
  println("inc9999(10) = " + inc9999(10))

  // TODO 8.8. Специальные формы вызова функций
  // Повторяющиеся параметры
  println("========= 8.8.  Повторяющиеся параметры  =========")
  def echo(args: String*) = {   // повторяющийся параметр -  знак звездочки
    for (arg <- args) {
      print(arg + "  ") }
    print("\n")
  }
  echo("one")
  echo("hello", "world!")

  // Передача массива в функцию с повторяющимся параметром
  val arr = Array("What's", "up", "doc?")
  echo(arr: _*) // после аргумента в виде массива поставить двоеточие, знак подчеркивания и знак звездочки

  def speed(distance: Float, time: Float): Float = distance / time
  println(speed(100, 10))
  println(speed(time=10, distance = 100)) // именованные аргументы позволяют менять порядок аргументов

  def printTime(out: java.io.PrintStream = Console.out) = out.println("time = " + System.currentTimeMillis())
  def printTime2(out: java.io.PrintStream = Console.out, divisor: Int = 1) = out.println("time = " + System.currentTimeMillis()/divisor)

  printTime()
  printTime2(out = Console.err, divisor = 1000)
  printTime2(divisor = 1000)

}

// TODO 9. Управляющие абстракции
// TODO 9.1. Сокращение повторяемости кода


object FileMatcher {
    private def filesHere: Array[File] = new java.io.File(".").listFiles

  /**
   * Располагая новым вспомогательным методом по имени
   * filesMatching, можно упростить три поисковых метода, заставив
   * их вызывать вспомогательный метод, передавая в него
   * соответствующую функцию
   */
    def filesMatching(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }

  def filesEnding(query: String) =
    filesMatching(query, _.endsWith(_))
  def filesContaining(query: String) =
    filesMatching(query, _.contains(_))
  def filesRegex(query: String) =
    filesMatching(query, _.matches(_))

  println("++++++  in FileMatcher  +++++++++")
  println(filesEnding(".txt").mkString(" "))

}

// TODO Листинг 9.1. Использование замыканий для сокращения повторяющихся фрагментов кода
object FileMatcher1 {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName)) yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))
  def filesContaining(query: String) =
    filesMatching(_.contains(query))
  def filesRegex(query: String) =
    filesMatching(_.matches(query))

  println("++++++  in FileMatcher  +++++++++")
  println(filesEnding(".txt").mkString(" "))

  // TODO 9.2. Упрощение клиентского кода
  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0)
        exists = true
    exists
  }

  def containsNeg1(nums: List[Int]) = nums.exists(_ < 0)


  println("containsNeg(List(1, 2, 3, 4)) = "+containsNeg(List(1, 2, 3, 4)))
  println("containsNeg(List(1, 2, -3, 4)) = "+containsNeg(List(1, 2, -3, 4)))

}


