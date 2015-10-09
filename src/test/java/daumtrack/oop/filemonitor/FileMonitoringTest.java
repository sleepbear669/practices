package daumtrack.oop.filemonitor;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sleepbear on 2015. 10. 8..
 */
public class FileMonitoringTest {


    private static String noneFilePath;

    @BeforeClass
    public static void setUp() throws Exception {
        noneFilePath = "/tmp/test";

    }

    @Before
    public void setupBefore() {
        File file = new File(noneFilePath);
        file.mkdirs();
    }

    @After
    public void after() throws Exception {
        File file = new File(noneFilePath);
        file.delete();
    }

    @Test
    public void testPathConstructor() throws Exception {
        // Given
        FileMonitoring fileMonitoring = new FileMonitoring(noneFilePath);
        // When

        // Then
        assertThat(fileMonitoring.getPath(), is(noneFilePath));
    }

}
