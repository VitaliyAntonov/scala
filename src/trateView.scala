

// TODO Листинг 12.1. Определение трейта Philosophical
trait Philosophical {
  def philosophize() = {
    println("I consume memory, therefore I am!")
  }
}

// TODO Листинг 12.2. Подмешивание трейта
//  с использованием ключевого слова extends
class Frog extends Philosophical {
  override def toString = "green"
}



// TODO Листинг 12.3. Подмешивание трейта
//  с использованием ключевого слова with
class Animal

class Frog1 extends Animal with Philosophical {
  override def toString = "green"
}

// TODO Листинг 12.4. Подмешивание сразу нескольких трейтов
trait HasLegs

class Frog2 extends Animal with Philosophical with HasLegs {
  override def toString = "green"

  /**
   *  метод philosophize в классе Frog может быть переопределен
   */
  override def philosophize() = {
    println("It ain't easy being " + toString +
      "!")
  }

}

// точки — Point и прямоугольник — Rectangle
class Point(val x: Int, val y: Int)

//class Rectangle(val topLeft: Point, val bottomRight: Point) {
//  def left = topLeft.x
//  def right = bottomRight.x
//  def width = right - left
//  // и множество других геометрических методов...
//}

// Листинг 12.5. Определение обогащающего трейта
trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // и множество других геометрических методов...
}

/**
 * трейт можно подмешать в класс Component, чтобы получить все
 * геометрические методы, предоставляемые Rectangular
 */
abstract class Component extends Rectangular {
  // другие методы...
}

/**
 * Точно так же трейт может подмешиваться в сам Rectangle:
 */
class
Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  // другие методы...
}

object demoTrait {
  val frog = new Frog2
  println(frog.philosophize)

  val phil: Philosophical = frog
  println(phil)
  println(phil.philosophize())

  /**
   *  можно создать Rectangle-объект и вызвать
   *  в отношении него геометрические методы width и left
   */
  val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
  println(rect.left + " " + rect.right + " " + rect.width)

}
