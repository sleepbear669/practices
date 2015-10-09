package daumtrack.oop.filemonitor;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by sleepbear on 2015. 10. 9..
 */
public class FileManager {

    private ArrayList<FileMetaData> fileMetaDatas = Lists.newArrayList();

    public void add(FileMetaData fileMetaData) {
        fileMetaDatas.add(fileMetaData);
    }

    public int getFileCount() {
        return fileMetaDatas.size();
    }
}
