package com.anson.bta.manager;

import com.anson.bta.main.Global;
import com.anson.bta.ui.diagUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InitManager {

    public static boolean systemFileStatus = false;

    public static void run() throws IOException {
        fileFolderManager();
    }

    private static void fileFolderManager() throws IOException {
        //Create System Folders
        Path systemFolder = Paths.get(Global.folderLocation+"\\System");
        Path dataFolder = Paths.get(Global.folderLocation+"\\Data");
        Files.createDirectories(Global.folderLocation);
        Files.createDirectories(systemFolder);
        Files.createDirectories(dataFolder);

        //Create System Files
        File foodType = new File(systemFolder+"\\category.dll");
        if(foodType.createNewFile()) {
            systemFileStatus = true;
            //Write into the file
            try {
                FileWriter writer = new FileWriter(foodType);
                writer.write(Global.defaultFoodList);
                writer.close();
            } catch (IOException e) {
                systemFileStatus = false;
                diagUI.errorBox(e.getMessage(), "系統故障");
            }

        }
    }
}
