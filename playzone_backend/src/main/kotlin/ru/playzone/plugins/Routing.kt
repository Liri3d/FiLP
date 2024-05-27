package ru.playzone.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Базовый класс - Сотрудник
open class Employee(val name: String, val age: Int, val position: String) {
    open fun calculateSalary(): Double {
        return 0.0
    }

    override fun toString(): String {
        return "Employee(name=$name, age=$age, position=$position)"
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
}

// Класс - Консультант
class Consultant(name: String, age: Int, val expertiseArea: String) : Employee(name, age, "Consultant") {
    override fun calculateSalary(): Double {
        // Логика расчета заработной платы для консультанта
        return super.calculateSalary() + 4000.0
    }
}

fun getEmployees(): List<Employee> {
    val hrManager1 = HRManager("Иванов", 35)
    val manager1 = Manager("Петров", 40, "Отдел продаж", "Sales")
    val consultant1 = Consultant("Сидорова", 28, "HR")

    val hrManager2 = HRManager("Смирнов", 30)
    val manager2 = Manager("Козлов", 45, "Отдел маркетинга", "Marketing")
    val consultant2 = Consultant("Иванова", 32, "Marketing")

    return listOf(hrManager1, manager1, consultant1, hrManager2, manager2, consultant2)
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Иерархия классов Сотрудники")
        }

        // Запрос: Самый старший сотрудник
        get("/most-senior-employee") {
            val oldestEmployee = getOldestEmployee()
            if (oldestEmployee != null) {
                call.respondText(oldestEmployee.toString())
            } else {
                call.respondText("Сотрудник не найден", status = HttpStatusCode.NotFound)
            }
        }

        // Запрос: Самый младший сотрудник
        get("/the-youngest-employee") {
            val youngestEmployee = getYoungestEmployee()
            if (youngestEmployee != null) {
                call.respondText(youngestEmployee.toString())
            } else {
                call.respondText("Сотрудник не найден", status = HttpStatusCode.NotFound)
            }
        }



        // Запрос: Средний возраст консультантов
        get("/average-age-of-consultants") {
            val averageAge = getAverageAge()
            if (averageAge != null) {
                call.respondText(averageAge.toString())
            } else {
                call.respondText("Средний возраст не найден", status = HttpStatusCode.NotFound)
            }
        }

        // Запрос: Процент сотрудников младше 30
        get("/percentage-of-employees-under-30") {
            val percentage = getPercentage()
            if (percentage != null) {
                call.respondText(percentage.toString())
            } else {
                call.respondText("Процент сотрудников не найден", status = HttpStatusCode.NotFound)
            }
        }

        // Запрос: Процент сотрудников старше 40 из отдела маркетинга
        get("/percentage-of-employees") {
            val percentage = getPpercentage()
            if (percentage != null) {
                call.respondText(percentage.toString())
            } else {
                call.respondText("Процент сотрудников не найден", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun getOldestEmployee(): Employee? {
    val employees: List<Employee> = getEmployees()
    return employees.maxByOrNull { it.age }
}

fun getYoungestEmployee(): Employee? {
    val employees: List<Employee> = getEmployees()
    return employees.minByOrNull { it.age }
}

fun getAverageAge(): Double {
    val employees: List<Employee> = getEmployees()
    val average = employees.filter { it.position == "Consultant" }
    return average.map { it.age }.average()
}

fun getPercentage(): Double {
    val employees: List<Employee> = getEmployees()
    return  ((employees.count { it.age < 30 }.toDouble() / employees.size) * 100)
}

fun getPpercentage(): Double {
    val employees: List<Employee> = getEmployees()
    val marketingEmployees = (employees.filter { it.position == "Отдел маркетинга" })
    return (marketingEmployees.count { it.age > 40 }.toDouble() / marketingEmployees.size) * 100
}