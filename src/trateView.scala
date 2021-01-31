

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
    println("It ain't easy being " + toString + "!")
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
class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  // другие методы...
}

// TODO 12.5. Трейты в качестве наращиваемых изменений

// TODO Листинг 12.6. Абстрактный класс IntQueue
/**
 * IntQueue содержит метод put, добавляющий к очереди новые
 * целые числа, и метод get, возвращающий целые числа и
 * удаляющий их из очереди
 */
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

// TODO Листинг 12.7. Реализация класса BasicIntQueue с использованием ArrayBuffer
import bobsdelights.Fruit

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = { buf += x }
}

// TODO Листинг 12.8. Трейт наращиваемых изменений Doubling
/**
 * Объявляется родительский класс IntQueue. Это означает, что трейт может
 * подмешиваться только в класс, который также расширяет IntQueue
 * Имеется вызов super в отношении метода, объявленного абстрактным
 */
trait Doubling extends IntQueue {
  abstract override def put(x: Int) = {
    super.put(2 * x) }
}

// TODO Применение трейта выглядит следующим образом:
class MyQueue extends BasicIntQueue with Doubling

// TODO Листинг 12.10. Трейты наращиваемых изменений Incrementing и Filtering
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = {
    super.put(x + 1) }
}

trait Incrementing2 extends IntQueue {
  abstract override def put(x: Int) = {
    super.put(x + 2) }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = {
    if (x >= 0) super.put(x)
  }
}




object demoTrait {
  val frog = new Frog2
  println(frog.philosophize())

  val phil: Philosophical = frog
  println(phil)
  println(phil.philosophize())

  /**
   *  можно создать Rectangle-объект и вызвать
   *  в отношении него геометрические методы width и left
   */
  println("=========  Rectangle-объект методы left right и width  ========")
  val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
  println(rect.left + " " + rect.right + " " + rect.width)

  println("========= Подмешивание трейтов =============")
  val queue = new MyQueue
  queue.put(10)
  println(queue.get())

  // TODO Листинг 12.9. Подмешивание трейта при создании экземпляра с
  //  помощью ключевого слова new
  println("========= Подмешивание трейтов при создании экземпляра =============")
  val queue1 = new BasicIntQueue with Doubling
  queue1.put(20)
  println(queue1.get())

  val queue2 = (new BasicIntQueue with Filtering with Doubling with Incrementing2)
  queue2.put(-1); queue2.put(0); queue2.put(1)
  println(queue2.get())
  println(queue2.get())
  println(queue2.get())

}


// TODO 13. Пакеты и импортируемый код

// TODO Листинг 13.4. Краткая форма обращения к классам и пакетам
package bobsrockets {

  package navigation {

    class Navigator {
      // Указывать bobsrockets.navigation.StarMap не нужно
      val map = new StarMap
    }

    class StarMap
  }

  class Ship {
    // Указывать bobsrockets.navigation.Navigator не нужно
    val nav = new navigation.Navigator
  }


  package fleets {

    class Fleet {
      // Указывать bobsrockets.Ship не нужно
      def addShip() = { new Ship }
    }

  }

}

// topic =======================================================



// ==========
// fixme Test fixme ==================================================================
// TODO 13.3. Импортирование кода
// TODO Листинг 13.7. Превосходные фрукты от Боба, готовые к импорту
package bobsdelights{
  abstract class Fruit(val name: String, val color: String)

  object Fruits {
    object Apple extends Fruit("apple", "red")
    object Orange extends Fruit("orange", "orange")
    object Pear extends Fruit("pear", "yellowish")
    var menu = List(Apple, Orange, Pear)
  }

}

// topic Листинг 13.9. Импортирование имени пакета
import java.util.regex
class AStarB {
  // Обращение к java.util.regex.Pattern
  val pat = regex.Pattern.compile("a*b")

}








