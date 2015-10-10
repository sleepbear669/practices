package daumtrack.oop.filemonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static daumtrack.oop.filemonitor.FileManager.*;
import static java.lang.Thread.sleep;

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

    public void monitoring() throws IOException, InterruptedException {
        fileManager = new FileManager(fileSearcher.search(path));
        printFileList(fileManager);
        while (true) {
            Map<FileMetaData, Diff> diff = fileManager.diff(new FileManager(fileSearcher.search(path)));
            printDiff(diff);
            sleep(1000);
        }
    }

    private void printDiff(Map<FileMetaData, Diff> diff) {
        for (Map.Entry<FileMetaData, Diff> f : diff.entrySet()) {
            logger.debug("{} : {} ", f.getKey().getPath(), f.getValue());
        }
    }

    private void printFileList(FileManager fileManager) {
        for (FileMetaData fileMetaData : fileManager.getFileMetaDataSet()) {
            logger.debug("{} : {} ", fileMetaData.getPath(), fileMetaData.getSize());
        }

    }

    public String getPath() {
        return path;
    }
}
