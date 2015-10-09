package daumtrack.oop.filemonitor;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileMetaData that = (FileMetaData) o;

        return path.equals(that.path);

    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
