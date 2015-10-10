package daumtrack.oop.filemonitor;

import com.google.common.base.Objects;

/**
 * Created by sleepbear on 2015. 10. 8..
 */
public class FileMetaData {

    private String path;
    private long size;
    private long lastModified;

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

        if (lastModified != that.lastModified) return false;
        return path.equals(that.path);

    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
