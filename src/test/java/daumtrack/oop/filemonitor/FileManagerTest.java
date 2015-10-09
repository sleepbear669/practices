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
    private HashSet<FileMetaData> fileMetaDataList = Sets.newHashSet();
    @Before
    public void setUp() throws Exception {
        fileManager = new FileManager();

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
        FileMetaData dummy1 = new FileMetaData("/tmp/test1.txt", 100, 20101010);
        FileMetaData dummy2 = new FileMetaData("/tmp/test2.txt", 100, 20101010);
        FileMetaData dummy3 = new FileMetaData("/tmp/test1.txt", 100, 20101010);

        // When
        fileManager.add(dummy1);
        fileManager.add(dummy2);
        fileManager.remove(dummy3);
        // Then

        assertThat(fileManager.getFileCount(), is(1));
    }
}