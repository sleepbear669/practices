package daumtrack.oop.filemonitor;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileSearcher {

    private ArrayList<FileMetaData> fileMetaDataList = Lists.newArrayList();

    public ArrayList<FileMetaData> search(String path) throws IOException {
        searchFile(path);
        return fileMetaDataList;
    }

    public void searchFile(String dirPath) throws IOException {
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
