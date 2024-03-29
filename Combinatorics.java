package combinatorics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Combinatorics {
    private static FileWriter fileWriter;

    public static void main(String[] args) {
        try {
            fileWriter = new FileWriter("combinatorial_objects.txt");
            
            printPermutations("", "ghj");
            printSubsets("", "123");
            printWordsWithThreeBs("", "abc");
            printWordsWithTwoDuplicates("", "qwerty");
            
            printPermutationsIterative("ghj");
            printSubsetsIterative("123");
            printWordsWithThreeBsIterative("abc");
            printWordsWithTwoDuplicatesIterative("qwerty");
            
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
    
    
    
    
    
    
    
   
    // Нерекурсивный метод для вывода всех перестановок
    private static void printPermutationsIterative(String str) throws IOException {
        int n = str.length();
        List<String> permutations = new ArrayList<>();
        permutations.add("");

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            List<String> newPermutations = new ArrayList<>();

            for (String permutation : permutations) {
                for (int j = 0; j <= permutation.length(); j++) {
                    String newPermutation = permutation.substring(0, j) + ch + permutation.substring(j);
                    newPermutations.add(newPermutation);
                }
            }

            permutations = newPermutations;
        }

        for (String permutation : permutations) {
            fileWriter.write(permutation + "\n");
        }
    }

    // Нерекурсивный метод для вывода всех подмножеств
    private static void printSubsetsIterative(String str) throws IOException {
        int n = str.length();
        int totalSubsets = 1 << n;

        for (int i = 0; i < totalSubsets; i++) {
            StringBuilder subset = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.append(str.charAt(j));
                }
            }

            fileWriter.write(subset.toString() + "\n");
        }
    }

    // Нерекурсивный метод для вывода всех слов длины 6 с 3 буквами 'b'
    private static void printWordsWithThreeBsIterative(String str) throws IOException {
        int n = str.length();
        char[] word = new char[6];

        for (int i = 0; i < n; i++) {
            word[0] = str.charAt(i);

            for (int j = 0; j < n; j++) {
                word[1] = str.charAt(j);

                for (int k = 0; k < n; k++) {
                    word[2] = 'b';
                    word[3] = 'b';
                    word[4] = 'b';

                    for (int l = 0; l < n; l++) {
                        word[5] = str.charAt(l);
                        fileWriter.write(new String(word) + "\n");
                    }
                }
            }
        }
    }

    // Нерекурсивный метод для вывода всех слов длины 6, где 2 буквы повторяются 2 раза
    // и остальные буквы не повторяются
    private static void printWordsWithTwoDuplicatesIterative(String str) throws IOException {
        int n = str.length();
        char[] word = new char[6];
        Set<Character> uniqueLetters = new HashSet<>();

        for (int i = 0; i < n; i++) {
            word[0] = str.charAt(i);

            for (int j = 0; j < n; j++) {
                word[1] = str.charAt(j);
                uniqueLetters.clear();
                uniqueLetters.add(word[0]);
                uniqueLetters.add(word[1]);

                for (int k = 0; k < n; k++) {
                    word[2] = str.charAt(k);

                    if (!uniqueLetters.contains(word[2])) {
                        uniqueLetters.add(word[2]);

                        for (int l = 0; l < n; l++) {
                            word[3] = str.charAt(l);

                            if (!uniqueLetters.contains(word[3])) {
                                uniqueLetters.add(word[3]);

                                for (int m = 0; m < n; m++) {
                                    word[4] = str.charAt(m);

                                    if (!uniqueLetters.contains(word[4])) {
                                        uniqueLetters.add(word[4]);

                                        for (int o = 0; o < n; o++) {
                                            word[5] = str.charAt(o);

                                            if (!uniqueLetters.contains(word[5])) {
                                                fileWriter.write(new String(word) + "\n");
                                            }
                                        }

                                        uniqueLetters.remove(word[4]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    
    
}