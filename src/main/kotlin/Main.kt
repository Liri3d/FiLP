import java.lang.System.`in`
import java.util.*

class Main {

// Реализовать функцию с телом выражение max(X,Y,Z), находящую максимальное из чисел X, Y и Z.
    fun max(x: Int, y: Int, z: Int): Int = if (x > y) if (x > z) x else z else if (y > z) y else z

// Реализовать функцию fact(N), с помощью рекурсии вверх, рекурсии вниз.
    fun factup(n: Int): Int = if (n <= 1) 1 else factup(n - 1) * n

    tailrec fun factd(n: Int, a: Int): Int = if (n <= 1) n * a else factd(n - 1, n * a)
    fun factdown(n: Int): Int = factd(n, 1)

// Найти сумму цифр числа с помощью рекурсии вверх/вниз.
    fun sumc(n: Int): Int = if (n <= 10) n else (n % 10) + (sumc(n / 10))

    fun sumcda(n: Int, a: Int): Int = if (n < 10) n + a else sumcda(n / 10, a + (n % 10))
    fun sumcd(n: Int): Int = sumcda(n, 0)

// Написать функцию, которая принимает один аргумент и возвращает функцию.
// Аргумент, тип логический, если он ИСТИНА, возвращаем функцию, считающую сумму цифр числа, если он ЛОЖЬ, возвращаем функцию, считающую факториал числа.
// Проверить эту функцию на нескольких примерах.
    fun calculate(f: Boolean): (Int) -> Int = if (f) ::sumc else ::factup

// Написать функцию обход числа, которая выполняет операции на цифрами числа, принимает три аргумента, число, функция (например, сумма, произведение, минимум,
// максимум) и инициализирующее значение. Функция должна иметь два Int аргумента и возвращать Int. Протестировать эту функцию на операциях сумма, произведение,
// минимум, максимум цифр числа. Для тестирования и передачи аргумента использовать лямбда выражения. Инициализирующее заполнение должно иметь значение по умолчанию.

    tailrec fun digits(n: Int, a: Int = 0, f: (Int, Int) -> Int): Int =
        if (n == 0) a else digits(n / 10, f(a, n % 10), f)

    fun sumd(n: Int): Int = digits(n, 0) { a, b -> (a + b) }
    fun muld(n: Int): Int = digits(n, 1) { a, b -> (a * b) }
    fun maxd(n: Int): Int = digits(n / 10, n % 10) { a, b -> if (a > b) a else b }
    fun mind(n: Int): Int = digits(n / 10, n % 10) { a, b -> if (a < b) a else b }

    fun main() {
        println("Задание 1 ------------------------------------------")
        println("Максимальное число (1,3,2): ${max(1, 3, 2)}")
        println("Факториал 3, рек.вверх: ${factup(3)}")
        println("Факториал 3, рек.вниз: ${factdown(3)}")
        println("Сумма цифр 1234 рек.вверх: ${sumc(1234)}")
        println("Сумма цифр 1234 рек.вниз: ${sumcd(1234)}")
        println("Если T -> sumc(11): ${calculate(true)(11)}")
        println("Если F -> factup(11): ${calculate(false)(11)}")
        println("Сумма цифр (234): ${sumd(234)}")
        println("Произведение цифр (234): ${muld(234)}")
        println("Макс цифра (234): ${maxd(234)}")
        println("Мин цифра (234): ${mind(234)}")
    }
}

fun main() = Main().main()