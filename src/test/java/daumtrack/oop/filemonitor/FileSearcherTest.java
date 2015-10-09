package daumtrack.oop.filemonitor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileSearcherTest {

    private String noneFileDirPath;

    @Before
    public void setUp() throws Exception {
        noneFileDirPath = "/tmp/gom/none";
        File file = new File(noneFileDirPath);
        file.mkdirs();
    }

    @After
    public void after() {
        File file = new File(noneFileDirPath);
        file.delete();
    }

    @Test
    public void testExistFileSearcher() throws Exception {
        // Given
        FileSearcher fileSearcher = new FileSearcher();
        // When

        // Then
        assertNotNull(fileSearcher);

    }

    @Test
    public void testNoneFileDirSearch() throws Exception {
        // Given
        FileSearcher fileSearcher = new FileSearcher();
        // When
        ArrayList<FileMetaData> search = fileSearcher.search(noneFileDirPath);
        // Then
        assertThat(search.size(), is(0));
    }

    @Test
    public void testName() throws Exception {
        // Given

        // When

        // Then

    }
}