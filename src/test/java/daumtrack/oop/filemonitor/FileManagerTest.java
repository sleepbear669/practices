package daumtrack.oop.filemonitor;

import org.junit.Test;

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
}