
/**
 * Реактивный мониторинг файловой системы с использованием актеров Akka
 * https://coderlessons.com/articles/java/reaktivnyi-monitoring-failovoi-sistemy-s-ispolzovaniem-akterov-akka
 * */

//
//import java.nio.file.{Path, Paths, WatchEvent}
//import scala.reflect.internal.util.NoPosition.contains
//
//class FileWatcher(file: Path) extends ThreadFileMonitor(file) with Actor {
//  import FileWatcher._
//
//  // MultiMap from Events to registered callbacks
//  protected[this] val callbacks = newMultiMap[Event, Callback]
//
//  // Override the dispatcher from ThreadFileMonitor to inform the actor of a new event
//  override def dispatch(event: Event, file: Path) = self ! Message.NewEvent(event, file)
//
//  // Override the onException from the ThreadFileMonitor
//  override def onException(exception: Throwable) = self ! Status.Failure(exception)
//
//  // when actor starts, start the ThreadFileMonitor
//  override def preStart() = super.start()
//
//  // before actor stops, stop the ThreadFileMonitor
//  override def postStop() = super.interrupt()
//
//  override def receive = {
//    case Message.NewEvent(event, target) if callbacks contains event =>
//      callbacks(event) foreach {f => f(event -> target)}
//
//    case Message.RegisterCallback(events, callback) =>
//      events foreach {event => callbacks.addBinding(event, callback)}
//
//    case Message.RemoveCallback(event, callback) =>
//      callbacks.removeBinding(event, callback)
//  }
//}
//
//object FileWatcher {
//  type Event = WatchEvent.Kind[Path]
//  type Callback = PartialFunction[(Event, Path), Unit]
//
//  sealed trait Message
//  object Message {
//    case class NewEvent(event: Event, file: Path) extends Message
//    case class RegisterCallback(events: Seq[Event], callback: Callback) extends Message
//    case class RemoveCallback(event: Event, callback: Callback) extends Message
//  }
//}
//// Это позволяет нам динамически регистрировать и удалять обратные вызовы, чтобы реагировать на события файловой системы:
//
//// initialize the actor instance
//val system = ActorSystem("mySystem")
//val watcher: ActorRef = system.actorOf(Props(new FileWatcher(Paths.get("/home/pathikrit"))))
//
//// util to create a RegisterCallback message for the actor
//def when(events: Event*)(callback: Callback): Message = {
//  Message.RegisterCallback(events.distinct, callback)
//}
//
//// send the register callback message for create/modify events
//watcher ! when(events = ENTRY_CREATE, ENTRY_MODIFY) {
//  case (ENTRY_CREATE, file) => println(s"$file got created")
//  case (ENTRY_MODIFY, file) => println(s"$file got modified")
//}
//


