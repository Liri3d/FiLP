package main2;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите кол-во элементов: ");
//        int size = scanner.nextInt();
//        int[] arr = new int[size];
//
//        System.out.println("Введите элементы:");
//        for (int i = 0; i < size; i++) {
//            arr[i] = scanner.nextInt();
//        }
//
//        int sum = arrayOp(arr, (a, b) -> a + b, 0);
//        int product = arrayOp(arr, (a, b) -> a * b, 1);
//        int min = arrayOp(arr, (a, b) -> a < b ? a : b, Integer.MAX_VALUE);
//        int max = arrayOp(arr, (a, b) -> a > b ? a : b, Integer.MIN_VALUE);
//
//        System.out.println("Сумма: " + sum);
//        System.out.println("Произведение: " + product);
//        System.out.println("Минимум: " + min);
//        System.out.println("Максимум: " + max);
//        
//        int mostFrequentElement = findMostFrequentElement(arr);
//        System.out.println("Самый частый элемент: " + mostFrequentElement);
//
//        List<Integer> evenRepeatedElements = findEvenRepeatedElements(arr);
//        System.out.println("Четные элементы, повторяющиеся четное число раз: " + evenRepeatedElements);
//
//        List<Integer> filteredList = filterNegativeNumbers(arr);
//        System.out.println("Список без отрицательных чисел с суммой цифр < 10: " + filteredList);
        
        
        
        
        
        
        
        
        
        
        
        User user1 = new User("Фам", "Им", "От", LocalDate.of(1999, 7, 2), "@a");
        User user2 = new User("Иванов", "Иван", "Иванович", LocalDate.of(1964, 2, 12), "@ivan");
        User user3 = new User("Василий", "Пупкин", "", LocalDate.of(1964, 2, 23), "@lesnik");
        User user4 = new User("Иванов", "Иван", "Иванович", LocalDate.of(1964, 2, 12), "@ivan");

        List<User> userList = new ArrayList();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        
      
        System.out.println("Пользователи:");
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println("");
                
        Set<User> hashSet = new HashSet<>();
        hashSet.add(user1);
        hashSet.add(user2);
        hashSet.add(user3);
        hashSet.add(user4);

        
        System.out.println("HashSet элементы:");
        for (User user : hashSet) {
            System.out.println(user);
        }

        System.out.println("\nОтсортированы по дате рождения:");
        Set<User> treeSet = new TreeSet<>(hashSet);
        for (User user : treeSet) {
            System.out.println(user);
        }

        System.out.println("\nПроверка равенства и поиск:");
        User searchUser = new User("Фам", "Им", "От", LocalDate.of(1999, 7, 2), "@a");
        System.out.println("Поиск" + searchUser);
        System.out.println("Пользователь1 равен searchUser? " + user1.equals(searchUser));
        System.out.println("Содержит ли hashSet searchUser? " + hashSet.contains(searchUser));
        System.out.println("Содержит ли treeSet searchUser? " + treeSet.contains(searchUser));
    }


    private static <T> T arrayOp(int[] arr, BiFunction<T, Integer, T> op, T init) {
        T result = init;
        for (int num : arr) {
            result = op.apply(result, num);
        }
        return result;
    }
    
    
    private static int findMostFrequentElement(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        return frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);
    }

    private static List<Integer> findEvenRepeatedElements(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            if (num % 2 == 0) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
        }

        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() % 2 == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static List<Integer> filterNegativeNumbers(int[] arr) {
        return Arrays.stream(arr)
                .filter(num -> !(num < 0 && sumOfDigits(Math.abs(num)) < 10))
                .boxed()
                .collect(Collectors.toList());
    }

    private static int sumOfDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
