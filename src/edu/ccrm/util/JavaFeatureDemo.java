package edu.ccrm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class demonstrates various Java language features and technical requirements
 */
public class JavaFeatureDemo {
    
    /**
     * Demonstrates operator precedence
     * Order: 
     * 1. () parentheses
     * 2. * / multiplication and division
     * 3. + - addition and subtraction
     * 4. &amp;&amp; logical AND
     * 5. || logical OR
     */
    public static int operatorPrecedenceDemo(int a, int b, int c) {
        // Without parentheses: multiplication happens before addition
        int result1 = a + b * c;        // Example: 1 + 2 * 3 = 7 (not 9)
        
        // With parentheses: addition happens first
        int result2 = (a + b) * c;      // Example: (1 + 2) * 3 = 9
        
        // Complex expression with multiple operators
        boolean complex = a > 0 && b > 0 || c > 0;  // AND evaluated before OR
        
        return complex ? result1 : result2;
    }

    /**
     * Demonstrates all loop types and a labeled break
     */
    public static void loopDemo(int[][] matrix) {
        // Traditional for loop with counter
        for (int i = 0; i < 10; i++) {
            if (i == 5) continue; // Skip 5
            System.out.println("Counter: " + i);
        }

        // Enhanced for loop (for-each)
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        // While loop
        int whileCounter = 0;
        while (whileCounter < 5) {
            System.out.println("While: " + whileCounter++);
        }

        // Do-while loop
        int doCounter = 0;
        do {
            System.out.println("Do-while: " + doCounter++);
        } while (doCounter < 3);

        // Labeled break example
        outerLoop: 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    System.out.println("Found -1 at position: " + i + "," + j);
                    break outerLoop; // Breaks both loops
                }
            }
        }
    }

    /**
     * Demonstrates assertions for validation
     */
    public static double calculateAverage(int[] numbers) {
        // Pre-condition
        assert numbers != null : "Input array cannot be null";
        assert numbers.length > 0 : "Input array cannot be empty";

        double sum = 0;
        for (int num : numbers) {
            // Invariant
            assert num >= 0 : "Numbers must be non-negative";
            sum += num;
        }

        double average = sum / numbers.length;
        
        // Post-condition
        assert average >= 0 : "Average must be non-negative";
        return average;
    }

    /**
     * Demonstrates anonymous inner class and lambda expressions
     */
    public static List<String> sortAndFilterStrings(List<String> input) {
        // Anonymous inner class implementation of Comparator
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        // Lambda expression implementation of Predicate
        Predicate<String> nonEmptyFilter = s -> !s.trim().isEmpty();

        List<String> result = new ArrayList<>(input);
        result.sort(lengthComparator);
        result.removeIf(s -> !nonEmptyFilter.test(s));
        return result;
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        // Operator precedence demo
        System.out.println("Operator Precedence: " + operatorPrecedenceDemo(1, 2, 3));

        // Loop demo
        int[][] matrix = {{1, 2}, {3, -1}, {5, 6}};
        loopDemo(matrix);

        // Assertions demo (run with -ea flag)
        try {
            double avg = calculateAverage(new int[]{1, 2, 3, 4, 5});
            System.out.println("Average: " + avg);
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
        }

        // Anonymous class and lambda demo
        List<String> strings = Arrays.asList("Hello", "", "World", "  ", "Java");
        List<String> filtered = sortAndFilterStrings(strings);
        System.out.println("Filtered and sorted: " + filtered);
    }
}
