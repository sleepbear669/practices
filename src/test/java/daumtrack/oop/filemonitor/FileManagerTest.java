package daumtrack.oop.filemonitor;

import com.google.common.collect.Sets;
import daumtrack.oop.filemonitor.FileManager.Diff;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileManagerTest {


    private FileManager fileManager;
    private HashSet<FileMetaData> fileMetaDataSet;

    FileMetaData dummy1;
    FileMetaData dummy2;
    FileMetaData dummy3;
    FileMetaData sameDummy1;

    @Before
    public void setUp() throws Exception {
        fileManager = new FileManager(Sets.<FileMetaData>newHashSet());
        fileMetaDataSet = Sets.newHashSet();
        dummy1 = new FileMetaData("/tmp/test1.txt", 100, 20101010);
        dummy2 = new FileMetaData("/tmp/test2.txt", 100, 20101010);
        dummy3 = new FileMetaData("/tmp/test3.txt", 100, 20101010);
        sameDummy1 = new FileMetaData("/tmp/test1.txt", 100, 20101010);

    }

    @Test
    public void testAddOneFile() throws Exception {
        // Given
        fileMetaDataSet.add(dummy1);
        // When
        fileManager = new FileManager(fileMetaDataSet);
        int fileCount = fileManager.getFileCount();
        // Then
        assertThat(fileCount, is(1));
    }

    @Test
    public void testRemoveOneFile() throws Exception {
        // Given

        // When
        fileManager.add(dummy1);
        fileManager.add(dummy2);
        fileManager.remove(sameDummy1);
        // Then

        assertThat(fileManager.getFileCount(), is(1));
    }

    @Test
    public void testCanAddFileMetaDataSet() throws Exception {
        // Given
        fileMetaDataSet.add(dummy1);
        fileMetaDataSet.add(dummy2);
        fileMetaDataSet.add(dummy3);
        // When
        fileManager.add(fileMetaDataSet);
        // Then
        assertThat(fileManager.getFileCount(), is(3));
    }

    @Test
    public void testAddSameData() throws Exception {
        // Given
        // When
        fileManager.add(dummy1);
        fileManager.add(sameDummy1);

        // Then
        assertThat(fileManager.getFileCount(), is(1));

    }

    @Test
    public void testNoChangeFileManager() throws Exception {
        // Given
        fileMetaDataSet.add(dummy1);
        fileMetaDataSet.add(dummy2);
        fileManager.add(fileMetaDataSet);
        FileManager noChangerFileManager = new FileManager(fileMetaDataSet);
        // When
        Map<FileMetaData, Diff> diff = fileManager.diff(noChangerFileManager);
        // Then
        assertThat(diff.size(), is(0));

    }

    @Test
    public void testDiffFileManagerWhenAdd() throws Exception {
        // Given
        fileMetaDataSet.add(dummy1);
        fileMetaDataSet.add(dummy2);
        fileManager.add(fileMetaDataSet);
        fileMetaDataSet.add(dummy3);
        FileManager addedOneItemFileManager = new FileManager(fileMetaDataSet);
        // When
        Map<FileMetaData, Diff> diffFileMetaDataMap = fileManager.diff(addedOneItemFileManager);
        // Then
        assertTrue(diffFileMetaDataMap.containsKey(dummy3));
        assertThat(diffFileMetaDataMap.get(dummy3), is(Diff.ADD));
        assertThat(diffFileMetaDataMap.size(), is(1));
        assertThat(fileManager.getFileCount(), is(3));
    }

    @Test
    public void testDiffFileManagerWhenDelete() throws Exception {
        // Given
        fileMetaDataSet.add(dummy1);
        fileMetaDataSet.add(dummy2);
        fileManager.add(fileMetaDataSet);
        fileMetaDataSet.remove(sameDummy1);
        FileManager deletedOneItemFileManager = new FileManager(fileMetaDataSet);
        // When
        Map<FileMetaData, Diff> diff = fileManager.diff(deletedOneItemFileManager);
        // Then
        assertTrue(diff.containsKey(dummy1));
        assertThat(diff.get(dummy1), is(Diff.DELETE));
        assertThat(diff.size(), is(1));
        assertThat(fileManager.getFileCount(), is(1));
    }
}