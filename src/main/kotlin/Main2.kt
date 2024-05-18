import java.lang.Math.abs
import java.lang.System.`in`
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.absoluteValue
import kotlin.math.sqrt


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

            val m: MutableList<Pasport> = mutableListOf()
            val u1=Pasport("Фам","Им","От", 1345, 678910, Date(1999,7,5),  "Женщина", "г. Москва", "УФМС России по Московской области", Date(2019, 2, 6), 956346)
            val u2=Pasport("Иванов", "Иван", "Иванович", 1234, 123456, Date(1964, 2, 2), "Мужчина", "г. Санкт-Петербург", "УФМС России по Ленинградской области", Date(1984, 4, 27), 208650)
            val u3=Pasport("Синицын", "Дмитрий", "Игоревич",  1123, 456789, Date(1964, 2, 3), "Мужчина", "г. Ростов-на-Дону", "УФМС России по Ростовской области", Date(1984, 1, 21), 457090)
            val u4=Pasport("Иванов", "Иван", "Иванович", 1234, 123456, Date(1964, 2, 2), "Мужчина", "г. Санкт-Петербург", "УФМС России по Ленинградской области", Date(1984, 4, 27), 208650)
            m.add(u1)
            m.add(u2)
            m.add(u3)
            m.add(u4)
            for (x in m)x.write()
            println("-------------------------------------------------------------------------------")
            m.sort()
            for (x in m)x.write()
            println("-------------------------------------------------------------------------------")
            val h: HashSet<Pasport> = hashSetOf()
            h.add(u1)
            h.add(u2)
            h.add(u3)
            h.add(u4)
            for (x in h)x.write()


    }
}

fun main() = Main2().main()