package daumtrack.oop.filemonitor;

import org.slf4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * Created by sleepbear on 2015. 10. 11..
 */
public class LoggerPrinter {
    private Logger logger;

    public LoggerPrinter(Logger logger) {
        this.logger = logger;
    }

    public void printChangedFiles(Map<Diff, Set<String>> diff) {
        diff.entrySet().stream()
                .filter( d -> !d.getValue().isEmpty())
                .forEach( d -> {
                    for (String path : d.getValue()) {
                        logger.debug("{} : {} ", d.getKey(), path);
                    }
                });

    }

    public void printFileList(FileManager fileManager) {
        for (FileMetaData fileMetaData : fileManager.getFileMetaDataSet()) {
            logger.debug("{} : {} ", fileMetaData.getPath(), fileMetaData.getSize());
        }

    }


}
