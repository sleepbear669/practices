package daumtrack.oop.filemonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * FileMonitor.
 *
 * @author mitchell.geek
 */
public class FileMonitor {


    private static final int TEST_FILE_NUMBER = 5;
    private String path;
    private File targetFile;
    public int fileCount;
    public FileMonitor(String path) {
        this.path = path;
        targetFile = new File(path);
    }

    public void searchFile() throws IOException {
        searchDirectory(targetFile, path);
    }

    private void searchDirectory(File file, String absPath) throws IOException {
        File[] files = file.listFiles();
        for (File subFile : files) {
            if (subFile.isDirectory()) {
                searchDirectory(subFile, absPath);
            } else {
                logger.debug("relative_path : {} {} ", subFile.getName(), subFile.length());
            }
            fileCount++;
        }
    }

    static Logger logger = LoggerFactory.getLogger("logger");

    public static void main(String[] args) throws IOException, InterruptedException {

        String path = args[0];
        for (int i = 0; i < TEST_FILE_NUMBER; i++) {
            MakeDummyFile(path);
        }
        new FileMonitoring(path).monitoring();
    }
    private static void MakeDummyFile(String testPath) throws IOException {
        String filePath = String.valueOf(Paths.get(testPath, String.valueOf(UUID.randomUUID())));
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filePath)));
        writer.write(String.valueOf(UUID.randomUUID()));
        writer.close();
    }
}
