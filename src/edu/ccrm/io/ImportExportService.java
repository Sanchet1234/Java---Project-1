package edu.ccrm.io;

import java.util.List;

public interface ImportExportService<T> {
    List<T> importFromFile(String filePath);
    void exportToFile(List<T> data, String filePath);
}
