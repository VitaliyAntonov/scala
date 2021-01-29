

// TODO Листинг 10.9. Класс Element с методами above, beside и toString

//abstract class Element {
//
//  def contents: Array[String]
//
//  def width: Int =
//    if (height == 0) 0 else contents(0).length
//
//  def height: Int = contents.length
//
//  def above(that: Element): Element =
//    new ArrayElement(this.contents ++ that.contents)
//
//  def beside(that: Element): Element =
//    new ArrayElement(for ( (line1, line2) <- this.contents zip that.contents
//      ) yield line1 + line2 )
//
//  override def toString = contents mkString "\n"
//}

// TODO Листинг 10.11. Класс Element, реорганизованный для
//  использования фабричных методов
import Element.elem
abstract class Element {

  def contents: Array[String]

  def width: Int =
    if (height == 0) 0 else contents(0).length

  def height: Int = contents.length

  def above(that: Element): Element =
    elem(this.contents ++ that.contents)

  def beside(that: Element): Element =
    elem( for ( (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2 )

  override def toString = contents mkString "\n"
}


class ArrayElement(val contents: Array[String]) extends Element {

}

// TODO Листинг 10.6. Вызов конструктора родительского класса
class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1

  /**
   * метод для вывода на экран
   */
  def show = println(this.s)

  /**
   * метод для слияния с доп. стрингом
   * @param p - строка, добавляемая в конец строки элемента
   * @return создаёт новый объект класса LineElement
   */
  def plus(p: String) = new LineElement(this.s.concat(p))

  /**
   * Переопределение метода для извлечения строки из объекта LineElement
   * @return
   */
  override def toString = this.s
}

// TODO 10.9. Полиморфизм и динамическое связывание
class UniformElement( ch: Char,
                      override val width: Int,
                      override val height: Int
                    ) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}


object demoElement{
  val ae = new ArrayElement(Array("hello", "world"))
  println("ae.height = " + ae.height + "  ae.width = " + ae.width)

  val e: Element = new ArrayElement(Array("hello"))

  val l = new LineElement("LineElement")
  l.show
  l.plus("+++").show
  println(l.plus("======"))

  // TODO 11.4. Определение своих собственных классов значений
  class Dollars(val amount: Int) extends AnyVal {
    override def toString() = "$" + amount
  }

  class SwissFrancs(val amount: Int) extends AnyVal {
    override def toString() = amount + " CHF"
  }

  val money = new Dollars(1000000)
  println(money)
  println(money.amount)

  val francs = new SwissFrancs(1000)
  println(francs)
  println(francs.amount)

  // TODO классы для генерации HTML
  class Anchor(val value: String) extends AnyVal
  class Style(val value: String) extends AnyVal
  class Text(val value: String) extends AnyVal
  class Html(val value: String) extends AnyVal

  def title(text: Text, anchor: Anchor, style: Style): Html =
    new Html(
      s"<a id='${anchor.value}'>" +
        s"<h1 class='${style.value}'>" +
        text.value + "</h1></a>"
    )


}

// TODO 10.11. Использование композиции и наследования
// TODO 10.13. Определение фабричного объекта

// Листинг 10.10. Фабричный объект с фабричными методами
//object Element {
//
//  def elem(contents: Array[String]): Element =
//    new ArrayElement(contents)
//
//  def elem(chr: Char, width: Int, height: Int): Element =
//    new UniformElement(chr, width, height)
//
//  def elem(line: String): Element =
//    new LineElement(line)
//}

// TODO Листинг 10.12. Скрытие реализации
//  с помощью использования закрытых классов
object Element {
  private class ArrayElement(val contents: Array[String]) extends Element
  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }
  private class UniformElement( ch: Char,
                                override val width: Int,
                                override val height: Int
                              ) extends Element {
    private val line = ch.toString * width

    def contents = Array.fill(height)(line)
  }
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element = new LineElement(line)

}


