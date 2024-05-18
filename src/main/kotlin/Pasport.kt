import java.util.Date

// Задание 4
//Построить класс пользователь — Фамилия, Имя, Отчество, Дата рождения,
//телеграм. Реализовать вывод на экран, возможность сравнения по дате Рождения,
//возможность точного равенства, реализовать возможность добавления элементов в
//множество. Построить HashSet TreeSet с элементами этого класса, проверить корректность
//работы lkjhgпоиска элементов.

class Pasport(var lastname : String, var name : String, var fathername : String = "",
              var pasportNumber : Int, var pasportSeries : Int, var birth : Date,
              var gender : String, var placeOfBirth : String, var whoGaveItAway : String,
              var DateOfIssue : Date, var unitNumber : Int)  : Comparable<Pasport> {

    fun write() {
        println(
            "Пользователь\n" +
                    "Фамилия : $lastname\n" +
                    "Имя : $name\n" +
                    "Отчество : $fathername\n" +
                    "Номер паспорта : $pasportNumber\n" +
                    "Серия паспорта : $pasportSeries\n" +
                    "Дата рождения: ${birth.day}.${birth.month}.${birth.year}\n" +
                    "Пол : $gender\n" +
                    "Место рождения : $placeOfBirth\n" +
                    "Паспорт выдан : $whoGaveItAway\n" +
                    "Дата выдачи : $DateOfIssue\n" +
                    "Код подразделения : $unitNumber\n")
    }

    override fun equals(other: Any?): Boolean = if (other is Pasport) {
        (name == other.name) &&
                (lastname == other.lastname) &&
                (fathername == other.fathername) &&
                (pasportNumber == other.pasportNumber) &&
                (pasportSeries == other.pasportSeries) &&
                (birth == other.birth) &&
                (gender == other.gender) &&
                (placeOfBirth == other.placeOfBirth) &&
                (whoGaveItAway == other.whoGaveItAway) &&
                (DateOfIssue == other.DateOfIssue) &&
                (unitNumber == other.unitNumber)
    } else false

    override fun compareTo(other: Pasport): Int {
        return compareValuesBy(
            this, other,
            Pasport::birth, Pasport::lastname, Pasport::name, Pasport::fathername, Pasport::pasportNumber, Pasport::pasportSeries, Pasport::placeOfBirth, Pasport::whoGaveItAway, Pasport::DateOfIssue, Pasport::DateOfIssue, Pasport::unitNumber
        )
    }

    override fun hashCode(): Int {
        var result = birth.hashCode()
        result = 31 * result + lastname.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + fathername.hashCode()
        result = 31 * result + pasportNumber.hashCode()
        result = 31 * result + pasportSeries.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + placeOfBirth.hashCode()
        result = 31 * result + whoGaveItAway.hashCode()
        result = 31 * result + DateOfIssue.hashCode()
        result = 31 * result + unitNumber.hashCode()

        return result
    }
}