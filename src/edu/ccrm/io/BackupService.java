package edu.ccrm.io;

import java.nio.file.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    public void backupData(String sourceDir, String backupRootDir) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path sourcePath = Paths.get(sourceDir);
        Path backupPath = Paths.get(backupRootDir, "backup_" + timestamp);
        try {
            if (!Files.exists(backupPath)) {
                Files.createDirectories(backupPath);
            }
            Files.walk(sourcePath)
                .forEach(src -> {
                    try {
                        Path dest = backupPath.resolve(sourcePath.relativize(src));
                        if (Files.isDirectory(src)) {
                            if (!Files.exists(dest)) {
                                Files.createDirectories(dest);
                            }
                        } else {
                            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        System.err.println("Error copying file: " + src + " - " + e.getMessage());
                    }
                });
            System.out.println("Backup completed to: " + backupPath);
        } catch (IOException e) {
            System.err.println("Backup failed: " + e.getMessage());
        }
    }

    public long getDirectorySize(Path dir) {
        final long[] size = {0};
        try {
            Files.walk(dir)
                .filter(Files::isRegularFile)
                .forEach(p -> {
                    try {
                        size[0] += Files.size(p);
                    } catch (IOException e) {
                        System.err.println("Error reading file size: " + p + " - " + e.getMessage());
                    }
                });
        } catch (IOException e) {
            System.err.println("Error walking directory: " + dir + " - " + e.getMessage());
        }
        return size[0];
    }
}
