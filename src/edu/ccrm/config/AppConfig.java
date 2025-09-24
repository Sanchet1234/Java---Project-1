package edu.ccrm.config;

public class AppConfig {
    private static AppConfig instance;
    private String dataFolderPath;

    private AppConfig() {
        // Load config from file or set defaults
        this.dataFolderPath = "data";
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public String getDataFolderPath() {
        return dataFolderPath;
    }
    public void setDataFolderPath(String path) {
        this.dataFolderPath = path;
    }
}
