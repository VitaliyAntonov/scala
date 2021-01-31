import scala.collection.mutable.ListBuffer

/**
 * Инструментарий для индексирования файловой системы
 *
 * Связанные индексы для файлов
 *
 * 1 - ID файла
 * 2 - имя файла
 * 3 - ID папки, в которой находится файл
 *
 * Связанные индексы для папок
 * 1 - ID папки
 * 2 - имя папки
 * 3 - ID папки,в которую вложена
 *
 * ID файла или папки - это номер в массиве, полученный при генерации индекса
 *
 * Служебные массивы для контроля индесации
 *
 * массив флагов создания индекса
 * createIndexFlags
 * 1 - ID папки
 * 2 - флаги построения индекса папок - выставляется при построении индекса для всех вложенных папок
 * 3 - флаги построения индекса файлов - выставляется при построении индекса для всех вложенных файлов
 *
 * Переменные процесса индексации
 * 1 - startPath - путь стартовой папки, с которой начато построение индекса
 * 1 - currentPath - путь текущей папки, для которой строится индекс
 * 2 - newFolderID - ID следующей папки(свободный ID) при индексации(последний номер в массиве папок)
 * 3 - newFileID - ID следующего файла(свободный ID) при индксации(последний номер в массиве файлов)
 * 4 - timeDateIndex - время и дата построения индекса
 *
 * Устройство файлов для фиксации построенных индексов
 *
 * Вариант индекса вниз от заданной папки
 *  Директория, в которой находятся файлы индексов
 *  Основной файл <IndexHead.index> , в котором прописаны имена всех файлов созданных индексов
 *
 * ***  Формировать имя файла индекса от полного пути к индексируемой папке
 * - менять слэш на другой символ(например <subSlash> = "_")
 * - при наличии в пути знака _ заменять его на два таких знака <????>
 *
 * задать время обновления индекса
 * при устаревании индекса формировать второй файл с новым именем
 * по окончании формирования нового файла индекса предыдущий удалять.
 *
 * Формат фиксации индекса из массивов в файл
 *
 * каждая строка содержит одну запись из массива, поля разделены пробелом
 *
 * Индекс, файлов:
 * ключевое слово начала индекса <StartFilesIndex>
 * ID файла <пробел> имя файла <пробел> ID папки, в которой находится файл <enter>
 * ключевое слово окончания индекса <EndFilesIndex>
 *
 *
 * ID папки <пробел> имя папки <пробел> ID папки,в которую вложена
 */

// topic Индекс файловой системы
/**
  * базовый класс Ifs Индекс файловой системы
  */
abstract  class Ifs



/**
  * Создание списков вложенных файлов и папок внутри дирректории
  * с проверкой корректности входного пути <path>
  * @param path  для создания экземпляра используем полный путь к дирректории
  */
class Ifolder(path: String) extends Ifs {

  /** Проверка существует ли директория
    * @return Boolean */
  val pathOk: Boolean = new java.io.File(path).isDirectory

  // при наличии дирректории заполняем списки, иначе создаём нулевой список
  val foldersNames: List[String] =
    if (pathOk)
      (for (pathlist ← filesHere if pathlist.isDirectory) yield pathlist.getName).toList
    else null

  val filesNames: List[String] =
    if (pathOk)
      (for (pathlist <- filesHere if pathlist.isFile) yield pathlist.getName).toList
    else null

  def filesHere = new java.io.File(path).listFiles  // Лист текущей дирректории

  def show = {
    if(pathOk){
      println("Директория :   " + new java.io.File(path).getCanonicalPath)
      showFoldersNames
      showFilesNames
    }
    else println("Error Path")
  }

  /**
    * Отладочные методы
    */
  def showFilesHere = for(i <- filesHere) println(i.toString)

  def showFoldersNames = {
    print("Список папок : ")
    if(foldersNames.length > 0){
      println()
      for(i <- foldersNames) println(i.toString)}
    else println("No folders in directory")
  }

  def showFilesNames = {
    print("Список файлов : ")
    if(filesNames.length > 0){
      for(i <- filesNames) println(i.toString)}
    else println("No files in directory")
  }
}


object IfolderDemo {

  val t1 = System.currentTimeMillis()  // Измерение времени в миллисекундах

//  val i1 = new Ifolder("/home/vitaliy/develop/scala/scala/scala/TestEmptyDirectory/")
  val i1 = new Ifolder("/home/vitaliy/opt/jdk1.8.0_181/bin/")

  val duration = System.currentTimeMillis() - t1  // Измерение времени в миллисекундах

  //    i1.showFilesHere
  i1.show
  println("время создания списков :  " + duration)


}

