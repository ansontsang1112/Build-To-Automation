package com.anson.bta.manager;

import com.anson.bta.main.Global;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager {
    public static ArrayList<String> foodCategory;

    static {
        try {
            foodCategory = importCategory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static ArrayList importCategory() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(Global.folderLocation + "\\System\\category.dll");
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            data.add(reader.nextLine());
        }
        reader.close();

        return data;
    }
}
