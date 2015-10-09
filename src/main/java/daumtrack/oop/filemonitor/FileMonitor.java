package daumtrack.oop.filemonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * FileMonitor.
 *
 * @author mitchell.geek
 */
public class FileMonitor {


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

    public static void main(String[] args) throws IOException {

        String path = args[0];
        FileMonitor fileMonitor = new FileMonitor(path);
        fileMonitor.searchFile();
        System.out.println(new File(path).length());
        System.out.println(fileMonitor.fileCount);
    }
}
