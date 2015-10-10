package daumtrack.oop.filemonitor;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

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
        Set<FileMetaData> diffFileMetaDataSet = Sets.newHashSet(diffFileManager.getFileMetaDataSet());
        HashSet<FileMetaData> fileMetaDataSet = Sets.newHashSet(this.fileMetaDataSet);
        final HashMap<FileMetaData, Diff> fileMetaDataDiffMap = Maps.newHashMap();
        if (fileMetaDataSet.equals(diffFileMetaDataSet)) {
            return Maps.newHashMap();
        }else if (!fileMetaDataSet.containsAll(diffFileMetaDataSet)) {
            diffFileMetaDataSet.removeAll(fileMetaDataSet);
            diffFileMetaDataSet.stream().forEach(fileMetaData -> fileMetaDataDiffMap.put(fileMetaData, Diff.ADD));
            return fileMetaDataDiffMap;
        }
        return Maps.newHashMap();
    }
}
