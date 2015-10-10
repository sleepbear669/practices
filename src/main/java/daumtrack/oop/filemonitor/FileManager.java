package daumtrack.oop.filemonitor;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static daumtrack.oop.filemonitor.FileManager.Diff.MODIFY;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileManager {

    enum Diff {
        ADD , DELETE, MODIFY
    }

    private Set<FileMetaData> fileMetaDataSet = Sets.newHashSet();

    public FileManager(Set<FileMetaData> search) {
        fileMetaDataSet = Sets.newHashSet(search);
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
        this.fileMetaDataSet = Sets.newHashSet(fileMetaDataSet);
    }

    public Map<FileMetaData, Diff> diff(FileManager diffFileManager) {
        Set<FileMetaData> diffFileMetaDataSet = Sets.newHashSet(diffFileManager.getFileMetaDataSet());
        HashSet<FileMetaData> fileMetaDataSet = Sets.newHashSet(this.fileMetaDataSet);
        final HashMap<FileMetaData, Diff> fileMetaDataDiffMap = Maps.newHashMap();

        if (fileMetaDataSet.equals(diffFileMetaDataSet)) {
            return fileMetaDataDiffMap;
        }
        if (!fileMetaDataSet.equals(diffFileMetaDataSet) && fileMetaDataSet.size() == diffFileMetaDataSet.size()) {
            setFileMetaDataSet(diffFileMetaDataSet);
            diffFileMetaDataSet.removeAll(fileMetaDataSet);
            diffFileMetaDataSet.forEach(fileMetaData -> fileMetaDataDiffMap.put(fileMetaData, MODIFY));
            return fileMetaDataDiffMap;

        }
        if (!fileMetaDataSet.containsAll(diffFileMetaDataSet)) {
            setFileMetaDataSet(diffFileMetaDataSet);
            diffFileMetaDataSet.removeAll(fileMetaDataSet);
            diffFileMetaDataSet.stream().forEach(fileMetaData -> fileMetaDataDiffMap.put(fileMetaData, Diff.ADD));
        }
        diffFileMetaDataSet = Sets.newHashSet(diffFileManager.getFileMetaDataSet());
        fileMetaDataSet = Sets.newHashSet(this.fileMetaDataSet);
        if(!diffFileMetaDataSet.containsAll(fileMetaDataSet)){
            setFileMetaDataSet(diffFileMetaDataSet);
            fileMetaDataSet.removeAll(diffFileMetaDataSet);
            fileMetaDataSet.stream().forEach(fileMetaData -> fileMetaDataDiffMap.put(fileMetaData, Diff.DELETE));
        }
        return fileMetaDataDiffMap;
    }
}
