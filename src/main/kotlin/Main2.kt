import java.lang.Math.abs
import java.lang.System.`in`
import java.util.*
import kotlin.collections.HashSet

val scanner = Scanner(`in`)

fun vvodd(n: Int, m: MutableList<Int>) {
    if (n>0) {
        m.add(scanner.nextInt())
        vvodd(n-1, m)
    }
}
fun vvod(n: Int):MutableList<Int> {
    val m:MutableList<Int> = mutableListOf()
    vvodd(n, m)
    return m
}

tailrec fun arrayOP(m: MutableList<Int>,i: Int, a: Int = 0, f: (Int, Int) -> Int): Int =
    if (i <= 0) a else arrayOP(m,i-1, f(a, m[i-1]), f)

fun sumd(m: MutableList<Int>): Int = arrayOP(m, m.size, 0) { a, b -> (a + b) }
fun muld(m: MutableList<Int>): Int = arrayOP(m, m.size, 1) { a, b -> (a * b) }
fun maxd(m: MutableList<Int>): Int = arrayOP(m, m.size, m[0]) { a, b -> if (a > b) a else b }
fun mind(m: MutableList<Int>): Int = arrayOP(m, m.size, m[0]) { a, b -> if (a < b) a else b }

fun freq(m: MutableList<Int>): Int
{
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

fun new2freq(m: MutableList<Int>): MutableList<Int>
{
    if (m.isNotEmpty()) {
        val n:MutableList<Int> = mutableListOf()
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (x in m) map[x] = map.getOrDefault(x, 0) + 1
        for (x in map) if ((x.key % 2 == 0) and (x.value % 2 == 0)) n.add(x.key)
        return n
    } else return mutableListOf()
}

fun newlist(m: MutableList<Int>): MutableList<Int>
{
    if (m.isNotEmpty()) {
        val n:MutableList<Int> = mutableListOf()
        for (x in m) if ((x < 0) and ((Main().sumc(abs(x))) < 10)) n.add(x)
        return n
    } else return mutableListOf()
}

fun main() {
    println("Введите количество элементов: ")
    val n = scanner.nextInt()
    println("Введите элементы:")
    val myList = vvod(n)
    print("Максимальный элемент: ${maxd(myList)}\n")
    print("Минимальный элемент: ${mind(myList)}\n")
    print("Сумма элементов: ${sumd(myList)}\n")
    print("Произведение элементов: ${muld(myList)}\n")

    print("Наиболее часто встречаемый: ${freq(myList)}\n")
    print("Четные элементы, четное кол-во раз: ${new2freq(myList)}\n")
    print("Отриц. элем, сумма цифр модуля < 10: ${newlist(myList)}\n")


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
}