package main2;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

//Построить класс пользователь — Фамилия, Имя, Отчество, Дата рождения,
//телеграм. Реализовать вывод на экран, возможность сравнения по дате Рождения,
//возможность точного равенства, реализовать возможность добавления элементов в
//множество. Построить HashSet TreeSet с элементами этого класса, проверить корректность
//работы поиска элементов.

class User implements Comparable<User> {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String telegramHandle;

    public User(String lastName, String firstName, String middleName, LocalDate birthDate, String telegramHandle) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.telegramHandle = telegramHandle;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", telegramHandle='" + telegramHandle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return lastName.equals(user.lastName) &&
                firstName.equals(user.firstName) &&
                middleName.equals(user.middleName) &&
                birthDate.equals(user.birthDate) &&
                telegramHandle.equals(user.telegramHandle);
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + telegramHandle.hashCode();
        return result;
    }

    @Override
    public int compareTo(User other) {
        return this.birthDate.compareTo(other.birthDate);
    }
}
