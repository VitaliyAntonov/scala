


// TODO Листинг 10.1. Определение абстрактного метода и класса
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0
  else
    contents(0).length
}


class ArrayElement(val contents: Array[String]) extends Element {

}

// TODO Листинг 10.6. Вызов конструктора родительского класса
class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}



object demoElement{
  val ae = new ArrayElement(Array("hello", "world"))
  println("ae.height = " + ae.height + "  ae.width = " + ae.width)

  val e: Element = new ArrayElement(Array("hello"))

}


