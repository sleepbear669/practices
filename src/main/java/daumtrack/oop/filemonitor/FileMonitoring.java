package daumtrack.oop.filemonitor;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Created by sleepbear on 2015. 10. 8..
 */
public class FileMonitoring {

    private String path;
    private FileManager fileManager;

    private FileSearcher fileSearcher = new FileSearcher();
    private LoggerPrinter loggerPrinter = new LoggerPrinter(LoggerFactory.getLogger("logger"));
    private ChangeDiffFileFinder changeDiffFileFinder = new ChangeDiffFileFinder();

    public FileMonitoring(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void monitoring() throws IOException, InterruptedException {
        fileManager = getFileManager();
        loggerPrinter.printFileList(fileManager);
        while (true) {
            FileManager newFileManager = getFileManager();
            Map<Diff, Set<String>> diff = changeDiffFileFinder.diffFileTo(fileManager, newFileManager);
            fileManager = newFileManager;
            loggerPrinter.printChangedFiles(diff);
            sleep(1000);
        }
    }

    private FileManager getFileManager() throws IOException {
        return new FileManager(fileSearcher.search(path));
    }
}
