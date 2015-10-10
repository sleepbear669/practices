package daumtrack.oop.filemonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created by sleepbear on 2015. 10. 8..
 */
public class FileMonitoring {

    private String path;
    private Logger logger = LoggerFactory.getLogger("logger");

    private FileManager fileManager;
    private FileSearcher fileSearcher = new FileSearcher();


    public FileMonitoring(String path) {
        this.path = path;
    }

    public void monitoring() throws IOException {
        fileManager = new FileManager(fileSearcher.search(path));
        printFileList(fileManager);
        while (true) {
            return;
        }
    }

    private void printFileList(FileManager fileManager) {
        for (FileMetaData fileMetaData : fileManager.getFileMetaDataSet()) {
            logger.debug("{} : {} {}", fileMetaData.getPath(), fileMetaData.getSize(), fileMetaData.getLastModified());
        }

    }

    public String getPath() {
        return path;
    }
}
