package daumtrack.oop.filemonitor;

import com.google.common.collect.Sets;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileSearcher {

    private Set<FileMetaData> fileMetaDataList = Sets.newHashSet();

    public Set<FileMetaData> search(String path) throws IOException {
        searchFile(path);
        return fileMetaDataList;
    }

    private void searchFile(String dirPath) throws IOException {
        File dir = new File(dirPath);
        scanDirectory(dir, dirPath);
    }

    private void scanDirectory(File file, String absPath) throws IOException {
        File[] files = file.listFiles();
        for (File childFile : files) {
            if (childFile.isDirectory()) {
                scanDirectory(childFile, absPath);
            } else {
                String path = childFile.getPath().replace(absPath, "");
                long length = childFile.length();
                long modified = childFile.lastModified();
                fileMetaDataList.add(new FileMetaData(path, length, modified));
            }
        }
    }
}
