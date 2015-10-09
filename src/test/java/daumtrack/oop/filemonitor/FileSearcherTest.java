package daumtrack.oop.filemonitor;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileSearcherTest {

    @Test
    public void testExistFileSearcher() throws Exception {
        // Given
        FileSearcher fileSearcher = new FileSearcher();
        // When

        // Then
        assertNotNull(fileSearcher);

    }
}