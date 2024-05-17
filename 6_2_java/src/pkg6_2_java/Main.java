package pkg6_2_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static int countSquares(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(num -> {
                    double sqrt = Math.sqrt(num);
                    return sqrt * sqrt == num && numbers.contains((int) sqrt);
                })
                .count();
    }

    public static List<Triple<Integer, Integer, Integer>> generateTuples(List<Integer> listA, List<Integer> listB, List<Integer> listC) {
        List<Integer> sortedListA = listA.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Integer> sortedListB = listB.stream().distinct().sorted(Comparator.comparingInt(Main::sumOfDigits).thenComparingInt(Math::abs)).collect(Collectors.toList());
        List<Integer> sortedListC = listC.stream().distinct().sorted((a, b) -> countDivisors(b) - countDivisors(a)).collect(Collectors.toList());

        int n = Math.min(Math.min(sortedListA.size(), sortedListB.size()), sortedListC.size());

        return IntStream.range(0, n)
                .mapToObj(i -> new Triple<>(sortedListA.get(i), sortedListB.get(i), sortedListC.get(i)))
                .collect(Collectors.toList());
    }

    private static int sumOfDigits(int num) {
        return Integer.toString(Math.abs(num))
                .toString()
                .chars()
                .map(Character::getNumericValue)
                .sum();
    }

    private static int countDivisors(int num) {
        return (int) IntStream.rangeClosed(1, Math.abs(num))
                .filter(i -> num % i == 0)
                .count();
    }

    static class Triple<A, B, C> {
        final A first;
        final B second;
        final C third;

        Triple(A first, B second, C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ", " + third + ")";
        }
    }   
     
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(2, 4, 3, 16, 9, 55, 66));
        int squareCount = countSquares(numbers);
        System.out.println("Количество элементов, являющихся квадратами других элементов: " + squareCount); 
        
        List<Integer> listA = Arrays.asList(3, 2, 1, 5, 4);
        List<Integer> listB = Arrays.asList(12, 23, 34, 45, 56);
        List<Integer> listC = Arrays.asList(6, 8, 9, 12, 16);

        List<Triple<Integer, Integer, Integer>> tuples = generateTuples(listA, listB, listC);
        System.out.println(tuples);
    }
      
}
