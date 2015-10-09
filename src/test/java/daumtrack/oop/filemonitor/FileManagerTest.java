package daumtrack.oop.filemonitor;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        HashSet<FileMetaData> fileMetaDatas = new HashSet<>();
        ArrayList<Object> objects = new ArrayList<>();
        // When

        // Then

    }
}