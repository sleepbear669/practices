package daumtrack.oop.filemonitor;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        fileManager = new FileManager();
        fileMetaDataSet = Sets.newHashSet();
        dummy1 = new FileMetaData("/tmp/test1.txt", 100, 20101010);
        dummy2 = new FileMetaData("/tmp/test2.txt", 100, 20101010);
        dummy3 = new FileMetaData("/tmp/test3.txt", 100, 20101010);
        sameDummy1 = new FileMetaData("/tmp/test1.txt", 100, 20101010);

    }

    @Test
    public void testAddOneFile() throws Exception {
        // Given
        FileMetaData fileMetaData = new FileMetaData("/tmp/test1.txt", 100, 20101010);
        // When
        fileManager.add(fileMetaData);
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
}