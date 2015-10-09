package daumtrack.oop.filemonitor;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileSearcher {

    private ArrayList<FileMetaData> fileMetaDatas = Lists.newArrayList();

    public ArrayList<FileMetaData> search(String path) {
        return fileMetaDatas;
    }
//
//    public void searchFile() throws IOException {
//        searchDirectory(targetFile, path);
//    }
//
//    private void searchDirectory(File file, String absPath) throws IOException {
//        File[] files = file.listFiles();
//        for (File subFile : files) {
//            if (subFile.isDirectory()) {
//                searchDirectory(subFile, absPath);
//            } else {
//                logger.debug("relative_path : {} {} ", subFile.getName(), subFile.length());
//            }
//            fileCount++;
//        }
//    }
}
