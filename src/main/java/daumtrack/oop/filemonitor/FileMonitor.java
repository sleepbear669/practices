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

    static Logger logger = LoggerFactory.getLogger("logger");

    public static void main(String[] args) throws IOException, InterruptedException {

        String path = args[0];
        new File(path).mkdirs();
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
