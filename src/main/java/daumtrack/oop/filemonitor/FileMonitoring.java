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

    private FileSearcher fileSearcher = new FileSearcher();
    private LoggerPrinter loggerPrinter = new LoggerPrinter(LoggerFactory.getLogger("logger"));

    public FileMonitoring(String path) {
        this.path = path;
    }

    public void monitoring() throws IOException, InterruptedException {
        FileManager fileManager = new FileManager(fileSearcher.search(path));
        loggerPrinter.printFileList(fileManager);
        while (true) {
            Map<Diff, Set<String>> diff = getDifferenceMap(fileManager);
            loggerPrinter.printDiffFile(diff);
            sleep(1000);
        }
    }

    private Map<Diff, Set<String>> getDifferenceMap(FileManager fileManager) throws IOException {
        FileManager newFileManager = new FileManager(fileSearcher.search(path));
        Map<Diff, Set<String>> diff = fileManager.diffFileTo(newFileManager);
        fileManager.setFileMetaDataSet( newFileManager.getFileMetaDataSet());
        return diff;
    }

    public String getPath() {
        return path;
    }
}
