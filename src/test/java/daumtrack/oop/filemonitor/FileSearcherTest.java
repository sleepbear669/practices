package daumtrack.oop.filemonitor;

import org.junit.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileSearcherTest {

    private static String noneFileDirPath;
    private FileSearcher fileSearcher;
    private static String testPath;
    private final int TEST_FILE_NUMBER = 5;

    @BeforeClass
    static public void start() throws Exception {
        testPath = "/tmp/test";
        noneFileDirPath = "/tmp/none";

        File noneDir = new File(noneFileDirPath);
        File testDir = new File(testPath);
        noneDir.mkdirs();
        testDir.mkdir();
    }

    @AfterClass
    static public void end() {
        File noneDir = new File(noneFileDirPath);
        File testDir = new File(testPath);
        noneDir.delete();
        testDir.delete();
    }

    @Before
    public void setUp() throws Exception {
        fileSearcher = new FileSearcher();

        for (int i = 0; i < TEST_FILE_NUMBER; i++) {
            MakeDummyFile(testPath);
        }
    }

    private void MakeDummyFile(String testPath) throws IOException {
        String filePath = String.valueOf(Paths.get(testPath, String.valueOf(UUID.randomUUID())));
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filePath)));
        writer.write(String.valueOf(UUID.randomUUID()));
        writer.close();
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(testPath);
        for (File file1 : file.listFiles()) {
            file1.delete();
        }
    }

    @Test
    public void testExistFileSearcher() throws Exception {
        // Given
        fileSearcher = new FileSearcher();
        // When

        // Then
        assertNotNull(fileSearcher);

    }

    @Test
    public void testNoneFileDirSearch() throws Exception {
        // Given

        // When
        ArrayList<FileMetaData> search = fileSearcher.search(noneFileDirPath);
        // Then
        assertThat(search.size(), is(0));
    }

    @Test
    public void testTestDirSearch() throws Exception {
        // Given

        // When
        ArrayList<FileMetaData> search = fileSearcher.search(testPath);
        // Then
        assertThat(search.size(), is(TEST_FILE_NUMBER));
    }
}