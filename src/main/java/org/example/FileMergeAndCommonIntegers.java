package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileMergeAndCommonIntegers {
    public static void main(String[] args) {
        String inputFile1 = "src/main/resources/input1.txt";
        String inputFile2 = "src/main/resources/input2.txt";
        String mergedFile = "src/main/resources/merged.txt";
        String commonFile = "src/main/resources/common.txt";

        List<Integer> list1 = readIntegersFromFile(inputFile1);
        List<Integer> list2 = readIntegersFromFile(inputFile2);


        System.out.println("Contents of input1.txt: " + list1);
        System.out.println("Contents of input2.txt: " + list2);

        if (list1 == null || list2 == null) {
            System.out.println("Error reading input files.");
            return;
        }

        List<Integer> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);

        writeIntegersToFile(mergedList, mergedFile);

        List<Integer> commonList = findCommonIntegers(list1, list2);

        // Debugging: Print the contents of commonList
        System.out.println("Common integers: " + commonList);

        writeIntegersToFile(commonList, commonFile);
    }

    private static List<Integer> readIntegersFromFile(String filename) {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    integers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in file " + filename + ": " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return null;
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            return null;
        }
        return integers;
    }

    private static void writeIntegersToFile(List<Integer> integers, String filename) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Integer integer : integers) {
                writer.write(integer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    private static List<Integer> findCommonIntegers(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }
}


