package daumtrack.oop.filemonitor;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static daumtrack.oop.filemonitor.Diff.*;

/**
 * Created by sleepbear on 2015. 10. 11..
 */
public class ChangeDiffFileFinder {

    public Map<Diff, Set<String>> diffFileTo(FileManager olderFileManager, FileManager diffFileManager) {
        Set<FileMetaData> diffFileMetaDataSet = Sets.newHashSet(diffFileManager.getFileMetaDataSet());
        Set<FileMetaData> olderFileMetaDataSet = Sets.newHashSet(olderFileManager.getFileMetaDataSet());

        Set<String> deletedFilePathSet = pathsOfDifferenceOfSets(olderFileMetaDataSet, diffFileMetaDataSet);
        Set<String> addedFilePathSet = pathsOfDifferenceOfSets(diffFileMetaDataSet, olderFileMetaDataSet);

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

    private Set<String> pathsOfDifferenceOfSets(Set<FileMetaData> rightSet, Set<FileMetaData> leftSet) {
        HashSet<FileMetaData> operationSet = Sets.newHashSet(rightSet);
        operationSet.removeAll(leftSet);
        return getFilePathSet(operationSet);
    }

    private Set<String> getFilePathSet(Set<FileMetaData> fileMetaDataSet) {
        return fileMetaDataSet.stream()
                .map(FileMetaData::getPath)
                .collect(Collectors.toSet());
    }

}
