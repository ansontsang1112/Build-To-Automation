package com.anson.bta.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager {
    public static ArrayList<String> fileNameIndicator(String path) {
        ArrayList<String> fileNames = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames.add(listOfFiles[i].getName());
            }
        }
        Collections.sort(fileNames);
        return fileNames;
    }
}
