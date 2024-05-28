
import java.lang.Math.abs
import java.lang.System.`in`
import java.util.*
import kotlin.math.absoluteValue

@Suppress("NON_TAIL_RECURSIVE_CALL")
class Main2 {
// Задание 1
//    Написать программу, которая читает массив Int с клавиатуры,
//    находит минимальный, максимальный элементы, сумму и произведение элементов. Для работы
//    написать одну функцию arrayOp(), перебирающую элементы массива, принимающую как
//    аргументы массив, лямбда выражение и инициализирующее значение. Написать 4 функции
//    для суммы, произведения, мин и макс, использующих функцию arrayOp().
    val scanner = Scanner(`in`)

    fun vvodd(n: Int, m: MutableList<Int>) {
        if (n > 0) {
            m.add(scanner.nextInt())
            vvodd(n - 1, m)
        }
    }

    fun vvod(n: Int): MutableList<Int> {
        val m: MutableList<Int> = mutableListOf()
        vvodd(n, m)
        return m
    }

    tailrec fun arrayOP(m: MutableList<Int>, i: Int, a: Int = 0, f: (Int, Int) -> Int): Int =
        if (i <= 0) a else arrayOP(m, i - 1, f(a, m[i - 1]), f)

    fun sumd(m: MutableList<Int>): Int = arrayOP(m, m.size, 0) { a, b -> (a + b) }
    fun muld(m: MutableList<Int>): Int = arrayOP(m, m.size, 1) { a, b -> (a * b) }
    fun maxd(m: MutableList<Int>): Int = arrayOP(m, m.size, m[0]) { a, b -> if (a > b) a else b }
    fun mind(m: MutableList<Int>): Int = arrayOP(m, m.size, m[0]) { a, b -> if (a < b) a else b }

    // Реализовать функцию, которая для заданного списка находит самый частый элемент.
    fun freq(m: MutableList<Int>): Int {
        if (m.isNotEmpty()) {
            val map: MutableMap<Int, Int> = mutableMapOf()
            for (x in m) map[x] = map.getOrDefault(x, 0) + 1
            var k = 1
            var e: Int = map.values.first()
            for (x in map) if (x.value > k) {
                k = x.value
                e = x.key
            }
            return e
        } else return 0
    }

    // Реализовать функцию, которая в из заданного списка строит новый, в котором есть
    //только четные элементы, которые повторяются в списке четное число раз
    fun new2freq(m: MutableList<Int>): MutableList<Int> {
        if (m.isNotEmpty()) {
            val n: MutableList<Int> = mutableListOf()
            val map: MutableMap<Int, Int> = mutableMapOf()
            for (x in m) map[x] = map.getOrDefault(x, 0) + 1
            for (x in map) if ((x.key % 2 == 0) and (x.value % 2 == 0)) n.add(x.key)
            return n
        } else return mutableListOf()
    }

    //Реализовать функцию, которая строит новый список на основе исходного, удалив из
    //него все отрицательные числа, сумма цифр которых меньше 10
    fun newlist(m: MutableList<Int>): MutableList<Int> {
        if (m.isNotEmpty()) {
            val n: MutableList<Int> = mutableListOf()
            for (x in m) if ((x < 0) and ((Main().sumc(abs(x)))< 10)) else n.add(x)
            return n
        } else return mutableListOf()
    }






    // Задание 2
//    Реализовать функцию, которая для данного списка указывает, сколько элементов из
//    него могут быть квадратом какого-то из элементов списка.
    fun countSquareElements(list: List<Int>): Int {
        val set = list.toSet() // Преобразуем список во множество, чтобы удалить дублирующиеся элементы
        return set.count { x -> set.contains(x * x) } // Подсчитываем количество элементов, для которых существует соответствующий квадратный корень в множестве
    }


//    Реализовать функцию, которая по трем спискам составляет список, состоящий из
//    кортежей длины 3, где каждый кортеж (ai,bi,ci) с номером I получен следующим образом:
    fun combineLists(list1: List<Int>, list2: List<Int>, list3: List<Int>): List<Triple<Int, Int, Int>> {
        val sortedList1 = list1.sortedDescending()
        val sortedList2 = list2.sortedBy { digitSum(it) }
        val sortedList3 = list3.sortedByDescending { countDivisors(it) }

        return sortedList1.indices.map { index ->
            Triple(sortedList1[index], sortedList2[index], sortedList3[index])
        }.sortedWith(compareByDescending<Triple<Int, Int, Int>> { it.first }
            .thenBy { it.second }
            .thenByDescending { it.third.absoluteValue })
    }

    fun digitSum(number: Int): Int {
        var sum = 0
        var num = number
        while (num != 0) {
            sum += num % 10
            num /= 10
        }
        return sum
    }

    fun countDivisors(number: Int): Int {
        var count = 0
        for (i in 1..number) {
            if (number % i == 0) {
                count++
            }
        }
        return count
    }



// Задание 3
//    4 Дан целочисленный массив. Вывести индексы массива в том порядке, в
//    котором соответствующие им элементы образуют убывающую последовательность.
    fun printIndicesInDescendingOrder(array: IntArray) {
        val indices = array.indices

        indices.sortedByDescending { array[it] }
            .forEach(::println)
    }
//    9 Дан целочисленный массив. Необходимо найти элементы, расположенные
//    перед последним минимальным.
    fun findElementsBeforeLastMin(array: IntArray): List<Int> {
        val lastMinIndex = array.lastIndexOf(array.minOrNull()!!)
        return array.take(lastMinIndex)
    }
//    21 Дан целочисленный массив. Необходимо найти элементы, расположенные
//    после первого максимального.
    fun findElementsAfterFirstMax(array: IntArray): List<Int> {
        val firstMaxIndex = array.indexOf(array.maxOrNull()!!)
        return array.drop(firstMaxIndex + 1)
    }
//    15 Дан целочисленный массив и натуральный индекс (число, меньшее размера
//    массива). Необходимо определить является ли элемент по указанному индексу
//    локальным минимумом.
    fun isLocalMinimum(array: IntArray, index: Int): Boolean {
        if (index < 0 || index >= array.size) {
            throw IndexOutOfBoundsException("Неверный индекс: $index")
        }

        val element = array[index]
        val leftElement = array.getOrNull(index - 1)
        val rightElement = array.getOrNull(index + 1)

        return (leftElement == null || element < leftElement) && (rightElement == null || element < rightElement)
    }
//    32 Дан целочисленный массив. Найти количество его локальных максимумов.
    fun countLocalMaxima(array: IntArray): Int {
        if (array.size < 3) {
            return 0
        }

        var count = 0

        for (i in 0 until array.size) {
            if (i == 0 && array[i] > array[i + 1]) {
                count++
            } else if (i == array.size - 1 && array[i] > array[i - 1]) {
                count++
            } else if (i > 0 && i < array.size - 1 && array[i] > array[i - 1] && array[i] > array[i + 1]) {
                count++
            }
        }

        return count
    }
//    29 Дан целочисленный массив и интервал a..b. Необходимо проверить наличие
//    максимального элемента массива в этом интервале.
    fun checkMaxInRange(array: IntArray, a: Int, b: Int): Boolean {
        if (array.isEmpty()) {
            return false
        }

        val maxElement = array.maxOrNull() ?: return false
        return maxElement in a..b
    }

    fun countFibonacciNumbers(arr: IntArray): Int {
        val fibonacciNumbers = mutableSetOf<Int>()
        var a = 0
        var b = 1
        fibonacciNumbers.add(a)
        fibonacciNumbers.add(b)

        val maxValue = arr.maxOrNull() ?: return 0 // Обработка пустого массива

        while (true) {
            val temp = b
            b = a + b
            a = temp
            if (b > maxValue) break
            fibonacciNumbers.add(b)
        }

        return arr.count { it in fibonacciNumbers }
    }


    // Задание 5


    data class Node(var data: String, var left: Node? = null, var right: Node? = null)

    class BinaryTree {
        var root: Node? = null

        fun insert(data: String) {
            root = insertRec(root, data)
        }

        private fun insertRec(root: Node?, data: String): Node {
            if (root == null) {
                return Node(data)
            }

            if (data.length < root.data.length) {
                root.left = insertRec(root.left, data)
            } else {
                root.right = insertRec(root.right, data)
            }

            return root
        }

        fun sortedList(): List<String> {
            val result = mutableListOf<String>()
            inOrderTraversal(root, result)
            return result
        }

        private fun inOrderTraversal(node: Node?, result: MutableList<String>) {
            if (node != null) {
                inOrderTraversal(node.left, result)
                result.add(node.data)
                inOrderTraversal(node.right, result)
            }
        }
    }

    fun printBinaryTree(root: Node?, indent: String = "", isLeft: Boolean = false) {
        if (root != null) {
            printBinaryTree(root.right, "$indent${if (isLeft) "       " else "        "}", false)
            println("$indent${root.data}")
            printBinaryTree(root.left, "$indent${if (isLeft) "        " else "       "}", true)
        }
    }

    fun createBinaryTree(strings: List<String>): BinaryTree {
        val binaryTree = BinaryTree()
        for (string in strings) {
            binaryTree.insert(string)
        }
        return binaryTree
    }

    fun createSortedList(binaryTree: BinaryTree): List<String> {
        return binaryTree.sortedList()
    }





    fun countLetters(str: String): Map<Char, Int> {
        val letterCounts = mutableMapOf<Char, Int>()

        for (char in str) {
            if (char.isLetter()) {
                val lowerChar = char.toLowerCase()
                letterCounts[lowerChar] = (letterCounts[lowerChar] ?: 0) + 1
            }
        }

        return letterCounts
    }

    fun TreeToLine(root: Node?): List<String> {
        val result = mutableListOf<String>()
        TreeToLineHelper(root, 0, result)
        print(result)
        val result1 = result.sortedByDescending {  str -> countLetters(str).size }
        return (result1)
    }

    private fun TreeToLineHelper(root: Node?, level: Int, result: MutableList<String>) {
        if (root == null) {
            return
        }

        // Добавляем значение узла в результат
        result.add("${root.data}")

        // Рекурсивно обрабатываем левое и правое поддерево
        TreeToLineHelper(root.left, level + 1, result)
        TreeToLineHelper(root.right, level + 1, result)
    }



    // Задание 6
    // Вариант 4 Задачи 40, 44, 51, 58
    // Дан целочисленный массив. Необходимо найти минимальный четный элемент
    fun findMinEven(arr: IntArray): Int? {
        return findMinEvenRecursive(arr, 0)
    }

    private tailrec fun findMinEvenRecursive(arr: IntArray, index: Int, minEven: Int? = null): Int? {
        if (index == arr.size) {
            return minEven // Возвращаем минимальное четное число или null, если четных элементов нет
        }

        val currentNumber = arr[index]
        if (currentNumber % 2 == 0) { // Проверяем, является ли число четным
            if (minEven == null || currentNumber < minEven) {
                return findMinEvenRecursive(arr, index + 1, currentNumber) // Обновляем minEven, если найдено меньшее четное число
            }
        }

        return findMinEvenRecursive(arr, index + 1, minEven) // Продолжаем поиск, если текущее число нечетное
    }


    //    1.44 Дан массив чисел. Необходимо проверить, чередуются ли в нем целые и
//    вещественные числа.
    fun checkAlternation(numbers: Array<Any?>): Boolean {
        if (numbers.isEmpty()) return false else return checkAlternationRecursive(numbers, 0, numbers[0] is Int)
    }

    private tailrec fun checkAlternationRecursive(numbers: Array<Any?>, index: Int, isInteger: Boolean): Boolean {
        if (index == numbers.size) {
            return true // Если дошли до конца массива, значит все чередуется корректно
        }

        val currentElement = numbers[index]
        if (currentElement == null) {
            return false // Если встретили null, возвращаем false
        }

        return when (currentElement) {
            is Int -> {
                if (isInteger) {
                    checkAlternationRecursive(numbers, index + 1, false)
                } else {
                    false
                }
            }
            is Double -> {
                if (!isInteger) {
                    checkAlternationRecursive(numbers, index + 1, true)
                } else {
                    false
                }
            }
            else -> {
                false // Если элемент не Int или Double, возвращаем false
            }
        }
    }


    //    1.51. Для введенного списка построить два списка L1 и L2, где элементы L1 это
//    неповторяющиеся элементы исходного списка, а элемент списка L2 с номером i показывает,
//    сколько раз элемент списка L1 с таким номером повторяется в исходном.
    fun buildLists(numbers: List<Any?>): Pair<List<Any?>, List<Int>> {
        return buildListsRecursive(numbers, mutableListOf(), mutableListOf())
    }

    private fun buildListsRecursive(
        remainingNumbers: List<Any?>,
        uniqueElements: MutableList<Any?>,
        countElements: MutableList<Int>
    ): Pair<List<Any?>, List<Int>> {
        if (remainingNumbers.isEmpty()) {
            return Pair(uniqueElements, countElements)
        }

        val head = remainingNumbers.first()
        val tail = remainingNumbers.drop(1)

        val index = uniqueElements.indexOf(head)
        if (index == -1) {
            uniqueElements.add(head)
            countElements.add(1)
            return buildListsRecursive(tail, uniqueElements, countElements)
        } else {
            countElements[index] += 1
            return buildListsRecursive(tail, uniqueElements, countElements)
        }
    }



    //    1.58 Для введенного списка вывести количество элементов, которые могут быть
//    получены как сумма двух любых других элементов списка.

    fun countPairSumElements(list: List<Int>): Int {
        return countPairSumElementsHelper(list, mutableSetOf(), 0, 1)
    }

    private fun countPairSumElementsHelper(list: List<Int>, seen: MutableSet<Int>, index: Int, i: Int): Int {
        if (index >= list.size - 1) {
            return 0
        }

        if (index + i >= list.size) {
            return countPairSumElementsHelper(list, seen, index + 1, 1)
        }

        val sum = list[index] + list[index + i]
        var count = 0
        if (sum in list && sum !in seen) {
            count++
            seen.add(sum)
        }
        if (i <= list.size) {
            count = count + countPairSumElementsHelper(list, seen, index, i + 1)
        }

        return count + countPairSumElementsHelper(list, seen, index + 1, 1)
    }





    // Задание 7
    // Вариант 4 Задачи 10, 1, 4
//    1 Даны две последовательности, найти наибольшую по длине общую
//    подпоследовательность.
    fun findLongestCommonSubstring(str1: String, str2: String): String {
        val substrings = str1.indices.flatMap { i ->
            str1.indices.filter { j -> j >= i }.map { j ->
                str1.substring(i, j + 1)
            }
        }.filter { substring ->
            str2.contains(substring)
        }.maxByOrNull { it.length }

        return substrings ?: ""
    }

//    4 Для введенного списка построить новый с элементами вида (a,b,c), где a<b<c образуют
//    Пифагорову тройку.

//    fun findPythagoreanTriplets(numbers: List<Int>): List<Triple<Int, Int, Int>> {
////        val triplets = mutableListOf<Triple<Int, Int, Int>>()
////
////        for (a in numbers) {
////            for (b in a + 1 until numbers.size) {
////                for (c in b + 1 until numbers.size) {
////                    if (a * a + b * b == c * c) {
////                        triplets.add(Triple(a, b, c))
////                    }
////                }
////            }
////        }
////
////        return triplets
////    }

    fun findPythagoreanTriplets(numbers: List<Int>): List<Triple<Int, Int, Int>> {
        return numbers.flatMap { a ->
            numbers.drop(numbers.indexOf(a) + 1).flatMap { b ->
                numbers.drop(numbers.indexOf(b) + 1).filter { c -> a * a + b * b == c * c }
                    .map { c -> Triple(a, b, c) }
            }
        }
    }

//    10 Для введенного списка вывести кортеж списков, составленных из
//    List2 - номера элементов, которые могут быть получены как произведение двух любых
//    других элементов списка
//    List3 - номера элементов, которые могут быть получены как сумма трех любых других
//    элементов списка.
//    LIST 4- номера элементов, которые делятся ровно на четыре элемента из списка

    // Элементы как произведение двух элементов
    fun findMultiplicationPairs(numbers: List<Int>): List<Int> {
        return numbers.flatMap { a ->
            numbers.filter { b -> a != b && numbers.contains(a * b) }
                .map { b -> a * b }
        }.distinct().toList()
    }

    fun findIndicesOfSecondListInFirstList(list1: List<Int>, list2: List<Int>): List<Int> {
        return list2.mapNotNull { num2 ->
            list1.indexOfFirst { num1 -> num1 == num2 }
        }
    }

    // Сумма трех элементов
    fun findSumOfThree(numbers: List<Int>): List<Int> {
        return numbers.flatMap { a ->
            numbers.drop(numbers.indexOf(a) + 1).flatMap { b ->
                numbers.drop(numbers.indexOf(b) + 1).mapNotNull { c ->
                    val sum = a + b + c
                    if (numbers.contains(sum)) sum else null
                }
            }
        }.distinct().sorted()
    }

    // Делятся на 4 элемента


    fun findDivisibleByFour(numbers: List<Int>): List<Int> {
        return numbers.filter { num ->
            numbers.count { it != num && num % it == 0 } == 4
        }.sorted()
    }









    fun main() {
        // Задание 1
//        println("Введите количество элементов: ")
//        val n = scanner.nextInt()
//        println("Введите элементы:")
//        val myList = vvod(n)
//        print("Максимальный элемент: ${maxd(myList)}\n")
//        print("Минимальный элемент: ${mind(myList)}\n")
//        print("Сумма элементов: ${sumd(myList)}\n")
//        print("Произведение элементов: ${muld(myList)}\n")
//
//        print("Наиболее часто встречаемый: ${freq(myList)}\n")
//        print("Четные элементы, четное кол-во раз: ${new2freq(myList)}\n")
//        print("Отриц. элем, сумма цифр модуля < 10: ${newlist(myList)}\n")


        //    val m: MutableList<User> = mutableListOf()
        //    val u1=User("Фам","Им","От","@a",Date(1999,7,2))
        //    val u2=User("Иванов","Иван","Иванович","@ivan",Date(1964,2,12))
        //    val u3=User("Василий","Пупкин","","@lesnik",Date(1988,10,30))
        //    val u4=User("Иванов","Иван","Иванович","@ivan",Date(1964,2,12))
        //    m.add(u1)
        //    m.add(u2)
        //    m.add(u3)
        //    m.add(u4)
        //    for (x in m)x.write()
        //    println("-------------------------------------------------------------------------------")
        //    m.sort()
        //    for (x in m)x.write()
        //    println("-------------------------------------------------------------------------------")
        //    val h: HashSet<User> = hashSetOf()
        //    h.add(u1)
        //    h.add(u2)
        //    h.add(u3)
        //    h.add(u4)
        //    for (x in h)x.write()

// Задание 2
//        val list = listOf(2, 3, 4, 6, 8, 9, 10)
//        val count = countSquareElements(list)
//        println("Количество элементов, которые являются квадратом какого-то другого элемента: $count")
//
//        val list1 = listOf(10, 5, 8, 3, 1)
//        val list2 = listOf(123, 456, 789, 234, 567)
//        val list3 = listOf(20, 15, 25, 30, 10)
//
//        val combinedList = combineLists(list1, list2, list3)
//        combinedList.forEach { (a, b, c) ->
//            println("($a, $b, $c)")
//        }



        // Задание 3

//        val array = intArrayOf(5, 2, 7, 3, 9, 1)
//        println("\nМассив: ${array.joinToString(", ")}")
//        println("Индексы убывающей последовательности:")
//        printIndicesInDescendingOrder(array)
//
//        val array1 = intArrayOf(5, 2, 7, 3, 9, 1, 2, 3, 1, 56)
//        println("\nМассив: ${array1.joinToString(", ")}")
//        val elements = findElementsBeforeLastMin(array1)
//        println("Элементы перед последним минимальным: ${elements.joinToString(", ")}")
//
//        val array2 = intArrayOf(5, 2, 7, 3, 9, 9, 1, 2, 3, 1)
//        println("\nМассив: ${array2.joinToString(", ")}")
//        val elements1 = findElementsAfterFirstMax(array2)
//        println("Элементы после первого максимального: ${elements1.joinToString(", ")}")
//
//        val array3 = intArrayOf(5, 2, 7, 3, 9, 1)
//        val index = 2
//        println("\nМассив: ${array3.joinToString(", ")}")
//        println("Индекс: ${index}")
//        val isLocalMin = isLocalMinimum(array3, index)
//        println("Элемент по индексу $index является локальным минимумом: $isLocalMin")
//
//        val array4 = intArrayOf(5, 1, 3, 2, 4, 1, 5, 2, 7)
//        println("\nМассив: ${array4.joinToString(", ")}")
//        val localMaximaCount = countLocalMaxima(array4)
//        println("Количество локальных максимумов: $localMaximaCount")
//
//        val array5 = intArrayOf(1, 3, 2, 4, 1, 5, 2, 7)
//        val a = 6
//        val b = 7
//        println("\nМассив: ${array5.joinToString(", ")}")
//        val maxInRange = checkMaxInRange(array5, a, b)
//        if (maxInRange) {
//            println("Максимальный элемент присутствует в интервале $a..$b")
//        } else {
//            println("Максимальный элемент отсутствует в интервале $a..$b")
//        }

        // Задание 4

//            val m: MutableList<Pasport> = mutableListOf()
//            val u1=Pasport("Фам","Им","От", 1345, 678910, Date(1999,7,5),  "Женщина", "г. Москва", "УФМС России по Московской области", Date(2019, 2, 6), 956346)
//            val u2=Pasport("Иванов", "Иван", "Иванович", 1234, 123456, Date(1964, 2, 2), "Мужчина", "г. Санкт-Петербург", "УФМС России по Ленинградской области", Date(1984, 4, 27), 208650)
//            val u3=Pasport("Синицын", "Дмитрий", "Игоревич",  1123, 456789, Date(1964, 2, 3), "Мужчина", "г. Ростов-на-Дону", "УФМС России по Ростовской области", Date(1984, 1, 21), 457090)
//            val u4=Pasport("Иванов", "Иван", "Иванович", 1234, 123456, Date(1964, 2, 2), "Мужчина", "г. Санкт-Петербург", "УФМС России по Ленинградской области", Date(1984, 4, 27), 208650)
//            m.add(u1)
//            m.add(u2)
//            m.add(u3)
//            m.add(u4)
//            for (x in m)x.write()
//            println("-------------------------------------------------------------------------------")
//            m.sort()
//            for (x in m)x.write()
//            println("-------------------------------------------------------------------------------")
//            val h: HashSet<Pasport> = hashSetOf()
//            h.add(u1)
//            h.add(u2)
//            h.add(u3)
//            h.add(u4)
//            for (x in h)x.write()

        // Доп задание
//        val arr = intArrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 380, 546)
//
//        val count = countFibonacciNumbers(arr)
//        println("Количество чисел Фибоначчи: $count") // Output: Number of Fibonacci numbers: 10

//        // Задание 5
//        val strings = listOf("adcd", "adcde", "ab", "adcdef", "abc", "a", "abcdefg")
//        val binaryTree = createBinaryTree(strings)
//        printBinaryTree(binaryTree.root)
//        print(TreeToLine(binaryTree.root))
//
//        // Задание 6
//
//        val myArray = intArrayOf(3, 7, 2, 9, 4, 1, 6)
//        val minEven = findMinEven(myArray)
//        println("\n\nМинимальный четный элемент ${myArray.contentToString()} ${minEven}")
//
//        val numbers = arrayOf<Any?>(2.5, 3, 4.7, 5, 6.1)
//        println("Чередуются целые и вещественные числа ${numbers.contentToString()} ${checkAlternation(numbers)}") // true
//
//        val inputList = listOf(1, 2.5, 3, 4.7, 3, 5, 6.1, 2.5)
//        val (l1, l2) = buildLists(inputList)
//        println("Построить два списка ${inputList} L1: ${l1} L2: ${l2}")
//
//        val list = listOf(2, 3, 4, 5, 6)
//        val count = countPairSumElements(list)
//        println("Кол-во эл., которые являются суммой двух других эл. ${list} Кол-во: ${count}")

//        // Задание 7
        val str1 = "DDFABCABCABCGADGBDGNRBDR"
        val str2 = "DDDABCABCABCDD"
        println(findLongestCommonSubstring(str1, str2))

        val numbers = listOf(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        val result = findPythagoreanTriplets(numbers)
        println(result)
        // (3, 4, 5), (5, 12, 13), (6, 8, 10), (8, 15, 17), (12, 16, 20) и (20, 21, 29).


        val numbers1 = listOf(72, 3, 48, 132, 4, 6, 8, 12, 13)
        val result1 = findMultiplicationPairs(numbers1)
        // println(result1)
        val result2 = findIndicesOfSecondListInFirstList(numbers1, result1)
        //println(result2)

        val result3 = findSumOfThree(numbers1)
        //  println(result3)
        val result4 = findIndicesOfSecondListInFirstList(numbers1, result3)
        //println(result4)

        val result5 = findDivisibleByFour(numbers1)
        // println(result5)
        val result6 = findIndicesOfSecondListInFirstList(numbers1, result5)
        //println(result6)

        val myTuple = Triple(result2, result4, result6)
        println(myTuple)

    }
}



fun main() = Main2().main()

//Решить задачи с помощью функционального подхода на языке Kotlin. (без использования циклов и foreach).

//Дан список. Построить списки: List1 - номера элементов, которые могут быть получены как произведение двух любых других элементов входного списка, List2 - номера элементов, которые могут быть получены как сумма трех любых других элементов входного списка, List3- номера элементов, которые делятся ровно на четыре элемента из входного списка