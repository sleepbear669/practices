package daumtrack.oop.filemonitor;

/**
 * Created by sleepbear on 2015. 10. 8..
 */
public class FileMetaData {

    String path;
    long size;
    long lastModified;

    public FileMetaData(String path, long size, long lastModified) {
        this.path = path;
        this.size = size;
        this.lastModified = lastModified;
    }

}
