package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileMergeAndCommonIntegers {
    public static void main(String[] args) {
        // File paths for the input and output files
        String inputFile1 = "src/main/resources/input1.txt";
        String inputFile2 = "src/main/resources/input2.txt";
        String mergedFile = "src/main/resources/merged.txt";
        String commonFile = "src/main/resources/common.txt";

        // Read integers from the two input files
        List<Integer> list1 = readIntegersFromFile(inputFile1);
        List<Integer> list2 = readIntegersFromFile(inputFile2);

        // Print the contents of the input files for debugging
        System.out.println("Contents of input1.txt: " + list1);
        System.out.println("Contents of input2.txt: " + list2);

        // Check if reading the files was successful
        if (list1 == null || list2 == null) {
            System.out.println("Error reading input files.");
            return;
        }

        // Merge the two lists into one
        List<Integer> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);

        // Write the merged list of integers to the output file
        writeIntegersToFile(mergedList, mergedFile);

        // Find the common integers between the two lists
        List<Integer> commonList = findCommonIntegers(list1, list2);

        // Print the common integers for debugging
        System.out.println("Common integers: " + commonList);

        // Write the common integers to the output file
        writeIntegersToFile(commonList, commonFile);
    }

    // Reads integers from a file and returns them as a list
    private static List<Integer> readIntegersFromFile(String filename) {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                try {
                    // Parse the line as an integer and add it to the list
                    integers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    // Handle the case where a line cannot be parsed as an integer
                    System.out.println("Invalid number format in file " + filename + ": " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            System.out.println("File not found: " + filename);
            return null;
        } catch (IOException e) {
            // Handle other IO errors
            System.out.println("Error reading file: " + filename);
            return null;
        }
        return integers;
    }

    // Writes a list of integers to a file
    private static void writeIntegersToFile(List<Integer> integers, String filename) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            // Write each integer to the file, one per line
            for (Integer integer : integers) {
                writer.write(integer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle IO errors during writing
            System.out.println("Error writing to file: " + filename);
        }
    }

    // Finds the common integers between two lists and returns them as a new list
    private static List<Integer> findCommonIntegers(List<Integer> list1, List<Integer> list2) {
        // Convert the first list to a set
        Set<Integer> set1 = new HashSet<>(list1);
        // Convert the second list to a set
        Set<Integer> set2 = new HashSet<>(list2);
        // Retain only the elements that are in both sets (intersection)
        set1.retainAll(set2);
        // Return the intersection as a list
        return new ArrayList<>(set1);
    }
}

