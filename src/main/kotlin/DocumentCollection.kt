

import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

// Abstract class DocumentCollection
abstract class DocumentCollection<Doc> {
    abstract fun searchDoc(doc: Doc): Boolean

    inline fun searchDocWithTime(doc: Doc): Pair<Boolean, Long> {
        val start = System.currentTimeMillis()
        val result = searchDoc(doc)
        val end = System.currentTimeMillis()
        return Pair(result, end - start)
    }
}

class ArrayDocumentCollection<Doc>(private val documents: Array<Doc>) : DocumentCollection<Doc>() {
    override fun searchDoc(doc: Doc): Boolean {
        return documents.contains(doc)
    }
}

class ListDocumentCollection<Doc>(private val documents: List<Doc>) : DocumentCollection<Doc>() {
    override fun searchDoc(doc: Doc): Boolean {
        return documents.contains(doc)
    }
}

class BinaryListDocumentCollection<Doc : Comparable<Doc>>(private val documents: List<Doc>) : DocumentCollection<Doc>() {
    override fun searchDoc(doc: Doc): Boolean {
        return documents.binarySearch(doc) >= 0
    }
}

class HashSetDocumentCollection<Doc>(private val documents: HashSet<Doc>) : DocumentCollection<Doc>() {
    override fun searchDoc(doc: Doc): Boolean {
        return documents.contains(doc)
    }
}

//class TreeSetDocumentCollection<Doc : Comparable<Doc>>(private val documents: TreeSet<Doc>) : DocumentCollection<Doc>() {
//    override fun searchDoc(doc: Doc): Boolean {
//        return documents.contains(doc)
//    }
//}

class TreeSetDocumentCollection<Doc : Comparable<Doc>>(private val documents: TreeSet<Doc>) : DocumentCollection<Doc>() {
    override fun searchDoc(doc: Doc): Boolean {
        return documents.contains(doc)
    }
}

fun readLinesFromFile(filePath: String, utf8: Charset): List<String> {
    val file = File(filePath)
    if (!file.exists()) {
        return emptyList()
    }

    try {
        return file.readLines()
    } catch (e: IOException) {
        println("Ошибка при чтении файла: $e")
        return emptyList()
    }
}

//fun checkSerialNumber(serialNumber: String, collection: DocumentCollection<String>): Triple<Boolean, Boolean, Long> {
//    val parts = serialNumber.split(" ")
//    if (parts.size != 2) {
//        return Triple(false, false, 0)
//    }
//
//    val (isValid, exists, foundTime) = when {
//        parts[0].length == 4 && parts[0].all { it.isDigit() } &&
//                parts[1].length == 6 && parts[1].all { it.isDigit() } -> {
//            val (found, time) = collection.searchDocWithTime(parts[1])
//            Triple(true, found, time)
//        }
//        else -> Triple(false, false, 0)
//    }
//
//    return Triple(isValid, exists, foundTime)
//}


fun main() {
    val databaseFile = "C:/Users/Administrator/Documents/FPLab5Example-master/database.docx"
    // Загружаем базу данных в HashSet для быстрого поиска
    val databaseSet = loadDatabaseFromFile(databaseFile)

    var validLinesTime = 0L
    var validLines: Long = 0
    var invalidLinesTime = 0L
    var invalidLines = 0
    var startTime: Long = 0

    val doc1 = "C:/Users/Administrator/Documents/FPLab5Example-master/Pasport1.docx"
    val doc2 = "C:/Users/Administrator/Documents/FPLab5Example-master/Pasport2.docx"
    val doc3 = "C:/Users/Administrator/Documents/FPLab5Example-master/Pasport3.docx"
    val arrayCollection = ArrayDocumentCollection(arrayOf(doc1, doc2, doc3))
    val checkFile = doc2
    val (found, time) = arrayCollection.searchDocWithTime(checkFile)
    if (found == true) {
        println("Файл найден, время поиска составило: $time мс\n")

        try {
            FileInputStream(checkFile).use { inputStream ->
                val document = XWPFDocument(inputStream)
                val extractor = XWPFWordExtractor(document)

                // Получаем содержимое файла построчно
                val lines = extractor.text.split("\n")
//                var validLines = 0
                //var invalidLines = 0
                for (line in lines) {
                    val trimmedLine = line.trim()
                    // if (isLineValid(trimmedLine)) {
                    startTime  = System.currentTimeMillis()
                    if (isLineValid(trimmedLine)) {
                        println("${trimmedLine}\nКорректные серия и номер ")
                        // startTime  = System.currentTimeMillis()
                        if (isLineValid(trimmedLine) && isLineInDatabase(trimmedLine, databaseSet)) {
                            println("Документ содержится в БД ")
                            val endValidTime = System.currentTimeMillis()
                            val timeWork = endValidTime - startTime
                            // println("start $startTime end $endValidTime work $timeWork")
                            validLinesTime += timeWork
                            validLines++
                            // println("+Время ${validLinesTime} кол-во +строк $validLines")
                        } else {
                            println("Документа ${trimmedLine} нет в БД")
                            val endinValidTime = System.currentTimeMillis()
                            val timeinWork = endinValidTime - startTime
                            invalidLinesTime += timeinWork
                            // println("invaltimesline $invalidLinesTime")
                            invalidLines++
                        }
                    }
                    else {
                        invalidLinesTime += (System.currentTimeMillis() - startTime).toInt()
                        invalidLines++
                        println("Некорректные серия и номер: ${trimmedLine}")
                    }

                    println()
                }
            }

        } catch (e: Exception) {
            println("Ошибка чтения файла: ${e.message}")
        }

    } else {println("Файл не найден")}

    println("Среднее время выполнения:")
//    println("validLiness $invalidLines")
//    println("validLinesTime $invalidLinesTime\n")
    //val work1 =  (validLinesTime.toDouble() / validLines.toDouble()).toDouble()
    println("Положительный ответ: ${String.format("%.2f", ((validLinesTime.toDouble()) / validLines))} с")
    println("Отрицательный ответ: ${String.format("%.2f", ((invalidLinesTime.toDouble()) / invalidLines))} с")



    val listCollection = ListDocumentCollection(listOf("doc1", "doc2", "doc3"))
    //println(listCollection.searchDoc("doc3")) // true

    val binaryListCollection = BinaryListDocumentCollection(listOf("doc1", "doc2", "doc3", "doc4", "doc5"))
    //println(binaryListCollection.searchDoc("doc3")) // true

    val hashSetCollection = HashSetDocumentCollection(hashSetOf("doc1", "doc2", "doc3"))
    //println(hashSetCollection.searchDoc("doc2")) // true

    val treeSetCollection = TreeSetDocumentCollection(TreeSet(listOf("doc1", "doc2", "doc3")))
    //println(treeSetCollection.searchDoc("doc2")) // true
}

private fun isLineValid(line: String): Boolean {
    val pattern = Pattern.compile("^\\d{4} \\d{6}$")
    val matcher = pattern.matcher(line)
    return matcher.matches()
}

private fun isLineInDatabase(line: String, databaseSet: HashSet<String>): Boolean {
    return databaseSet.contains(line)
}

private fun loadDatabaseFromFile(filePath: String): HashSet<String> {
    val database = HashSet<String>()

    try {
        FileInputStream(filePath).use { inputStream ->
            val document = XWPFDocument(inputStream)
            val extractor = XWPFWordExtractor(document)

            // Получаем содержимое файла построчно
            val lines = extractor.text.split("\n")

            for (line in lines) {
                database.add(line)
            }
        }
    } catch (e: Exception) {
        println("Ошибка чтения файла: ${e.message}")
    }

    return database
}