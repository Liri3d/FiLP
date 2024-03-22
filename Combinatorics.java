package combinatorics;

import java.io.FileWriter;
import java.io.IOException;

public class Combinatorics {
    private static FileWriter fileWriter;

    public static void main(String[] args) {
        try {
            fileWriter = new FileWriter("combinatorial_objects.txt");
            printPermutations("", "ghj");
            printSubsets("", "123");
            printWordsWithThreeBs("", "abc");
            printWordsWithTwoDuplicates("", "qwerty");
            fileWriter.close();
            System.out.println("В файл записаны комбинаторные объекты.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Рекурсивный метод для вывода всех перестановок
    private static void printPermutations(String prefix, String str) throws IOException {
        int n = str.length();
        if (n == 0) {
            fileWriter.write(prefix + "\n");
        } else {
            for (int i = 0; i < n; i++) {
                printPermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    // Рекурсивный метод для вывода всех подмножеств
    private static void printSubsets(String prefix, String str) throws IOException {
        if (str.isEmpty()) {
            fileWriter.write(prefix + "\n");
        } else {
            printSubsets(prefix, str.substring(1));
            printSubsets(prefix + str.charAt(0), str.substring(1));
        }
    }

    // Рекурсивный метод для вывода всех слов длины 6 с 3 буквами 'b'
    private static void printWordsWithThreeBs(String prefix, String str) throws IOException {
        if (prefix.length() == 6) {
            if (countOccurrences(prefix, 'b') == 3) {
                fileWriter.write(prefix + "\n");
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                printWordsWithThreeBs(prefix + str.charAt(i), str);
            }
        }
    }

    // Рекурсивный метод для вывода всех слов длины 6, где 2 буквы повторяются 2 раза
    // и остальные буквы не повторяются
    private static void printWordsWithTwoDuplicates(String prefix, String str) throws IOException {
        if (prefix.length() == 6) {
            if (hasExactlyTwoDuplicates(prefix)) {
                fileWriter.write(prefix + "\n");
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                printWordsWithTwoDuplicates(prefix + str.charAt(i), str);
            }
        }
    }  
   
    // Вспомогательный метод для подсчета количества вхождений символа в строку
    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    // Вспомогательный метод для проверки наличия ровно двух дубликатов в строке
    private static boolean hasExactlyTwoDuplicates(String str) {
        int[] counts = new int[26]; // assuming lowercase alphabets
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            counts[ch - 'a']++;
        }
        int duplicates = 0;
        for (int count : counts) {
            if (count == 2) {
                duplicates++;
            }
        }
        return duplicates == 2;
    }
}