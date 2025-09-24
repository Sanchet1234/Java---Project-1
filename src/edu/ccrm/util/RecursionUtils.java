package edu.ccrm.util;

import java.nio.file.*;
import java.io.IOException;

public class RecursionUtils {
    // Recursively print all files and directories by depth
    public static void printFilesByDepth(Path dir, int depth) {
        if (depth < 0) return;
        try {
            Files.list(dir).forEach(path -> {
                System.out.println(" ".repeat(depth * 2) + path.getFileName());
                if (Files.isDirectory(path)) {
                    printFilesByDepth(path, depth + 1);
                }
            });
        } catch (IOException e) {
            System.err.println("Error reading directory: " + dir + " - " + e.getMessage());
        }
    }

    // Recursively compute total number of files in a directory
    public static int countFiles(Path dir) {
        int count = 0;
        try {
            for (Path path : Files.newDirectoryStream(dir)) {
                if (Files.isDirectory(path)) {
                    count += countFiles(path);
                } else {
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading directory: " + dir + " - " + e.getMessage());
        }
        return count;
    }
}
