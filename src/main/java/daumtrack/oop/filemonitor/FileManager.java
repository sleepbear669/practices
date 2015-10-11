package daumtrack.oop.filemonitor;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static daumtrack.oop.filemonitor.FileManager.Diff.*;

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

    public Map<Diff, Set<String>> diffFileTo(FileManager diffFileManager) {
        Set<FileMetaData> diffFileMetaDataSet = Sets.newHashSet(diffFileManager.getFileMetaDataSet());
        Set<FileMetaData> olderFileMetaDataSet = Sets.newHashSet(fileMetaDataSet);

        Set<FileMetaData> deletedFileSet = differenceOfSets(olderFileMetaDataSet, diffFileMetaDataSet);
        Set<String> deletedFilePathSet = getFilePathSet(deletedFileSet);

        Set<FileMetaData> addedFileSet = differenceOfSets(diffFileMetaDataSet, olderFileMetaDataSet);
        Set<String> addedFilePathSet = getFilePathSet(addedFileSet);

        Set<String> modifiedFileSet = addedFilePathSet.stream()
                .filter(deletedFilePathSet::contains)
                .collect(Collectors.toSet());

        addedFilePathSet.removeAll(modifiedFileSet);
        deletedFilePathSet.removeAll(modifiedFileSet);

        HashMap<Diff, Set<String>> fileMetaDataDiffFileMap = Maps.newHashMap();
        fileMetaDataDiffFileMap.put(ADD, addedFilePathSet);
        fileMetaDataDiffFileMap.put(DELETE, deletedFilePathSet);
        fileMetaDataDiffFileMap.put(MODIFY, modifiedFileSet);

        return fileMetaDataDiffFileMap;
    }

    private Set<String> getFilePathSet(Set<FileMetaData> fileMetaDataSet) {
        return fileMetaDataSet.stream()
                    .map(FileMetaData::getPath)
                    .collect(Collectors.toSet());
    }

    private Set<FileMetaData> differenceOfSets(Set<FileMetaData> rightSet, Set<FileMetaData> leftSet) {
        HashSet<FileMetaData> operationSet = Sets.newHashSet(rightSet);
        operationSet.removeAll(leftSet);
        return operationSet;
    }

}
