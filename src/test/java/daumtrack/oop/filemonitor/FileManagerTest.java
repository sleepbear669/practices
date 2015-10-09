package daumtrack.oop.filemonitor;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileManagerTest {

    @Test
    public void testExistFileManager() throws Exception {
        // Given
        FileManager fileManager = new FileManager();
        // When

        // Then
        assertNotNull(fileManager);
    }

    @Test
    public void testAddOneFile() throws Exception {
        // Given
        FileManager fileManager = new FileManager();
        FileMetaData fileMetaData = new FileMetaData("/tmp/gom/test1.txt", 100, 20101010);
        // When
        fileManager.add(fileMetaData);
        int fileCount = fileManager.getFileCount();
        // Then
        assertThat(fileCount, is(1));
    }


}