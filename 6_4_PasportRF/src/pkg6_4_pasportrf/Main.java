/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6_4_pasportrf;
// Задание 4
//Построить класс пользователь — Фамилия, Имя, Отчество, Дата рождения,
//телеграм. Реализовать вывод на экран, возможность сравнения по дате Рождения,
//возможность точного равенства, реализовать возможность добавления элементов в
//множество. Построить HashSet TreeSet с элементами этого класса, проверить корректность
//работы поиска элементов.

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import pkg6_4_pasportrf.Pasport;
/**
 *
 * @author Liried
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pasport pasport1 = new Pasport("Фам", "Им", "От", 1345, 678910, LocalDate.of(1999, 7, 2), "Женщина", "г. Москва", "УФМС России по Московской области", LocalDate.of(2019, 2, 6), 956346);
        Pasport pasport2 = new Pasport("Иванов", "Иван", "Иванович", 1234, 123456, LocalDate.of(1964, 2, 12), "Мужчина", "г. Санкт-Петербург", "УФМС России по Ленинградской области", LocalDate.of(1984, 4, 27), 208650);
        Pasport pasport3 = new Pasport("Синицын", "Дмитрий", "Игоревич", 0123, 456789, LocalDate.of(1964, 2, 23), "Мужчина", "г. Ростов-на-Дону", "УФМС России по Ростовской области", LocalDate.of(1984, 1, 21), 457090);
        Pasport pasport4 = new Pasport("Иванов", "Иван", "Иванович", 1234, 123456, LocalDate.of(1964, 2, 12), "Мужчина", "г. Санкт-Петербург", "УФМС России по Ленинградской области", LocalDate.of(1984, 4, 27), 208650);

        List<Pasport> pasportList = new ArrayList();
        pasportList.add(pasport1);
        pasportList.add(pasport2);
        pasportList.add(pasport3);
        pasportList.add(pasport4);
        
      
        System.out.println("Паспорта:");
        for (Pasport pasport : pasportList) {
            System.out.println(pasport);
        }
        System.out.println("");
                
        Set<Pasport> hashSet = new HashSet<>();
        hashSet.add(pasport1);
        hashSet.add(pasport2);
        hashSet.add(pasport3);
        hashSet.add(pasport4);

        
        System.out.println("HashSet элементы:");
        for (Pasport pasport : hashSet) {
            System.out.println(pasport);
        }

        System.out.println("\nОтсортированы по дате рождения:");
        Set<Pasport> treeSet = new TreeSet<>(hashSet);
        for (Pasport pasport : treeSet) {
            System.out.println(pasport);
        }

        System.out.println("\nПроверка равенства и поиск:");
        Pasport searchPasport = new Pasport("Фам", "Им", "От", 1345, 678910, LocalDate.of(1999, 7, 2), "Женщина", "г. Москва", "УФМС России по Московской области", LocalDate.of(2019, 2, 6), 956346);
        System.out.println("Поиск" + searchPasport);
        System.out.println("Пользователь1 равен searchUser? " + pasport1.equals(searchPasport));
        System.out.println("Содержит ли hashSet searchUser? " + hashSet.contains(searchPasport));
        System.out.println("Содержит ли treeSet searchUser? " + treeSet.contains(searchPasport));
    }

    
    
}
