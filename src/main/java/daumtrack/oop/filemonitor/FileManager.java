package daumtrack.oop.filemonitor;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileManager {

    enum Diff {
        ADD , DELETE, MODIFY
    }

    private Set<FileMetaData> fileMetaDataSet = Sets.newHashSet();

    public FileManager(Set<FileMetaData> search) {
        fileMetaDataSet = search;
    }

    public void add(FileMetaData fileMetaData) {
        fileMetaDataSet.add(fileMetaData);
    }

    public void add(Set<FileMetaData> fileMetaDataSet) {
        this.fileMetaDataSet.addAll(fileMetaDataSet);
    }

    public int getFileCount() {
        return fileMetaDataSet.size();
    }

    public void remove(FileMetaData fileMetaData) {
        fileMetaDataSet.remove(fileMetaData);
    }

    public Set<FileMetaData> getFileMetaDataSet() {
        return fileMetaDataSet;
    }

    public void setFileMetaDataSet(Set<FileMetaData> fileMetaDataSet) {
        this.fileMetaDataSet = fileMetaDataSet;
    }

    public Map<FileMetaData, Diff> diff(FileManager diffFileManager) {
        Set<FileMetaData> diffFileMetaDataSet = diffFileManager.getFileMetaDataSet();
        if (fileMetaDataSet.equals(diffFileMetaDataSet)) {
            return Maps.newHashMap();
        }
        return null;
    }
}
