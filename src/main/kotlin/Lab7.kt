import com.google.gson.Gson
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// Класс-контейнер для списка объектов
data class EmployeeContainer(val employees: List<Employee>)

// Базовый класс - Сотрудник
open class Employee(val name: String, val age: Int, val position: String) {
    open fun calculateSalary(): Double {
        return 0.0
    }
}

// Класс - Руководитель
class Manager(name: String, age: Int, position: String, val department: String) : Employee(name, age, position) {
    override fun calculateSalary(): Double {
        // Логика расчета заработной платы для руководителя
        return super.calculateSalary() + 5000.0
    }
}

// Класс - Сотрудник отдела кадров
class HRManager(name: String, age: Int) : Employee(name, age, "HR Manager") {
    override fun calculateSalary(): Double {
        // Логика расчета заработной платы для сотрудника отдела кадров
        return super.calculateSalary() + 3000.0
    }

    fun hireEmployee(employee: Employee) {
        // Логика приема нового сотрудника
    }

    fun dismissEmployee(employee: Employee) {
        // Логика увольнения сотрудника
    }
}

// Класс - Консультант
class Consultant(name: String, age: Int, val expertiseArea: String) : Employee(name, age, "Consultant") {
    override fun calculateSalary(): Double {
        // Логика расчета заработной платы для консультанта
        return super.calculateSalary() + 4000.0
    }

    fun provideConsultation() {
        // Логика предоставления консультации
    }
}




// Пример использования классов
fun main() {
    // Задание 1
    val hrManager1 = HRManager("Иванов", 35)
    val manager1 = Manager("Петров", 40, "Отдел продаж", "Sales")
    val consultant1 = Consultant("Сидорова", 28, "HR")

    val hrManager2 = HRManager("Смирнов", 30)
    val manager2 = Manager("Козлов", 45, "Отдел маркетинга", "Marketing")
    val consultant2 = Consultant("Иванова", 32, "Marketing")

    val employees: List<Employee> = listOf(hrManager1, manager1, consultant1, hrManager2, manager2, consultant2)

//    for (employee in employees) {
//        println("Имя: ${employee.name}, Возраст: ${employee.age}, Должность: ${employee.position}, Зарплата: ${employee.calculateSalary()}")
//        println()
//    }

    // Задание 2-3
    // Создаем объект класса-контейнера с указанным списком объектов
    val container = EmployeeContainer(employees)

    // Сериализуем класс-контейнер в JSON
    val gson = Gson()
    val json = gson.toJson(container)

    // Сохраняем JSON в файл
    val filePath = "employees.json"
    File(filePath).writeText(json)

    // Десериализация объектов из файла
    val jsonContent = File(filePath).readText()
    val type: Type = object : TypeToken<EmployeeContainer>() {}.type
    val deserializedContainer: EmployeeContainer = gson.fromJson(jsonContent, type)

    // Получаем список объектов из класса-контейнера
    val deserializedEmployees: List<Employee> = deserializedContainer.employees

    // Вывод информации о каждом объекте
//    for (employee in deserializedEmployees) {
//        println("Имя: ${employee.name}, Возраст: ${employee.age}, Должность: ${employee.position}")
//    }

    // Задание 4
    val queryResults = mutableListOf<String>()
    // Запрос: Самый старший сотрудник
    val oldestEmployee = deserializedEmployees.maxByOrNull { it.age }
    if (oldestEmployee != null) {
        val oldestEmployeeResult = "Самый старший сотрудник: Имя: ${oldestEmployee.name}, Возраст: ${oldestEmployee.age}, Должность: ${oldestEmployee.position}"
        queryResults.add(oldestEmployeeResult)
    } else {
        queryResults.add("Список сотрудников пуст.")
    }

    // Запрос: Самый младший сотрудник
    val youngestEmployee = deserializedEmployees.minByOrNull { it.age }
    if (youngestEmployee != null) {
        val youngestEmployeeResult = "Самый младший сотрудник: Имя: ${youngestEmployee.name}, Возраст: ${youngestEmployee.age}, Должность: ${youngestEmployee.position}"
        queryResults.add(youngestEmployeeResult)
    } else {
        queryResults.add("Список сотрудников пуст.")
    }

    // Задание 5
    // Средний возраст консультантов
    val averageAgeOfConsultants = deserializedEmployees.filter { it.position == "Consultant" }
        .map { it.age }
        .average()
    val averageAgeOfConsultantsResult = "Средний возраст консультантов: $averageAgeOfConsultants"
    queryResults.add(averageAgeOfConsultantsResult)

    // Процент сотрудников младше 30
    val percentageYoungerThan30 = (deserializedEmployees.count { it.age < 30 }.toDouble() / deserializedEmployees.size) * 100
    val roundedPercentage = "%.1f".format(percentageYoungerThan30)
    val percentageYoungerThan30Result = "Процент сотрудников младше 30: $roundedPercentage%"
    queryResults.add(percentageYoungerThan30Result)

    // Процент сотрудников старше 40 из отдела маркетинга
    val marketingEmployees = deserializedEmployees.filter { it.position == "Отдел маркетинга" }
    val percentageOlderThan40InMarketing = (marketingEmployees.count { it.age > 40 }.toDouble() / marketingEmployees.size) * 100
    val percentageOlderThan40InMarketingResult = "Процент сотрудников старше 40 из отдела маркетинга: $percentageOlderThan40InMarketing%"
    queryResults.add(percentageOlderThan40InMarketingResult)

    for (result in queryResults) {
        println(result)
    }


}




