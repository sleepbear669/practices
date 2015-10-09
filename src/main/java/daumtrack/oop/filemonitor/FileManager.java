package daumtrack.oop.filemonitor;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileManager {

    private Set<FileMetaData> fileMetaDataSet = Sets.newHashSet();

    public void add(FileMetaData fileMetaData) {
        fileMetaDataSet.add(fileMetaData);
    }

    public int getFileCount() {
        return fileMetaDataSet.size();
    }

    public void remove(FileMetaData fileMetaData) {
        fileMetaDataSet.remove(fileMetaData);
    }
}
