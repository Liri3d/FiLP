package pkg6_4_pasportrf;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import pkg6_4_pasportrf.Main;

//Построить класс пользователь — Фамилия, Имя, Отчество, Дата рождения,
//телеграм. Реализовать вывод на экран, возможность сравнения по дате Рождения,
//возможность точного равенства, реализовать возможность добавления элементов в
//множество. Построить HashSet TreeSet с элементами этого класса, проверить корректность
//работы поиска элементов.

class Pasport implements Comparable<Pasport> {
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer passportNumber;
    private Integer passportSeries;
    private LocalDate birthDate;
    
    private String Gender;
    private String PlaceOfbirth;
    private String WhoGaveItAway;
    private LocalDate DateOfIssue;
    private Integer UnitNumber;
    
//    public void setPassportNumber(String passportNumber) {
//        if (passportNumber != null && passportNumber.matches("\\d{4}")) {
//            this.passportNumber = passportNumber;
//        } else {
//            throw new IllegalArgumentException("Номер паспорта должен состоять из 4 цифр");
//        }
//    }
    
    public Pasport(String lastName, String firstName, String middleName, Integer passportNumber, Integer passportSeries, LocalDate birthDate, String Gender, String PlaceOfbirth, String WhoGaveItAway, LocalDate DateOfIssue, Integer UnitNumber) {   
        this.lastName = lastName; 
        this.firstName = firstName; 
        this.middleName = middleName; 
        this.passportNumber = passportNumber; 
        this.passportSeries = passportSeries;
        this.birthDate = birthDate;
        
        this.Gender = Gender;
        this.PlaceOfbirth = PlaceOfbirth;
        this.WhoGaveItAway = WhoGaveItAway;
        this.DateOfIssue = DateOfIssue;
        this.UnitNumber = UnitNumber;
    }

    @Override
    public String toString() {
        return "Pasport{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", birthDate=" + birthDate +
                
                ", Gender=" + Gender +
                ", PlaceOfbirth=" + PlaceOfbirth +
                ", WhoGaveItAway=" + WhoGaveItAway +
                ", DateOfIssue=" + DateOfIssue +
                ", UnitNumber=" + UnitNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasport pasport = (Pasport) o;
        return lastName.equals(pasport.lastName) &&
                firstName.equals(pasport.firstName) &&
                middleName.equals(pasport.middleName) &&
                passportNumber.equals(pasport.passportNumber) &&
                passportSeries.equals(pasport.passportSeries) &&
                birthDate.equals(pasport.birthDate) &&
                Gender.equals(pasport.Gender) &&
                PlaceOfbirth.equals(pasport.PlaceOfbirth) &&
                WhoGaveItAway.equals(pasport.WhoGaveItAway) &&
                DateOfIssue.equals(pasport.DateOfIssue) &&
                UnitNumber.equals(pasport.UnitNumber);
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + passportNumber.hashCode();
        result = 31 * result + passportSeries.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + Gender.hashCode();
        result = 31 * result + PlaceOfbirth.hashCode();
        result = 31 * result + WhoGaveItAway.hashCode();
        result = 31 * result + DateOfIssue.hashCode();
        result = 31 * result + UnitNumber.hashCode();
        return result;
    }

    @Override
    public int compareTo(Pasport other) {
        return this.birthDate.compareTo(other.birthDate);
    }
}
