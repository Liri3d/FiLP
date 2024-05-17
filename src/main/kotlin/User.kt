import java.util.Date
// Задание 1
//Построить класс пользователь — Фамилия, Имя, Отчество, Дата рождения,
//телеграм. Реализовать вывод на экран, возможность сравнения по дате Рождения,
//возможность точного равенства, реализовать возможность добавления элементов в
//множество. Построить HashSet TreeSet с элементами этого класса, проверить корректность
//работы поиска элементов.

class User(var lastname : String, var name : String, var fathername : String = "",
           var tg : String = "", var birth : Date)  : Comparable<User> {

    fun write() {
        println(
            "Пользователь\n" +
                    "Фамилия : $lastname\n" +
                    "Имя : $name\n" +
                    "Отчество : $fathername\n" +
                    "Телега : $tg\n" +
                    "Дата рождения: ${birth.day}.${birth.month}.${birth.year}\n"
        )
    }

    override fun equals(other: Any?): Boolean = if (other is User) {
        (name == other.name) &&
                (lastname == other.lastname) &&
                (fathername == other.fathername) &&
                (birth == other.birth)
    } else false

    override fun compareTo(other: User): Int {
        return compareValuesBy(
            this, other,
            User::birth, User::lastname, User::name, User::fathername
        )
    }

    override fun hashCode(): Int {
        var result = birth.hashCode()
        result = 31 * result + lastname.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + fathername.hashCode()
        result = 31 * result + tg.hashCode()
        return result
    }
}