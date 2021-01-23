


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

}



