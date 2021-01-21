


object useCase{
  // TODO Листинг 7.14. Выражение сопоставления с побочными эффектами
  println("========= Use match ... case  ==========")
  val args = "salt"
  val firstArg = if (args.length > 0) args else ""
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
}
