package com.aprinz.ecsearch;

import android.util.Log;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexander on 20.07.2015.
 */
public class Extractor {
    private static final String TAG = "Extractor";

    public static Set<ErrorCode> extract(File file) {
        Set<ErrorCode> ecs = new HashSet<ErrorCode>();


        CSVReader reader = null;
        String[] nextLine;
        try {
            reader = new CSVReader(new FileReader(file), ';');
            while ((nextLine = reader.readNext()) != null) {
                Log.d(TAG, nextLine[0] + " / " + nextLine[1] + " / " + nextLine[2]);
                if (!nextLine[2].equals("Reserve")) {
                    ErrorCode ec = new ErrorCode(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6]);
                    ecs.add(ec);
                } else {
                    Log.d(TAG, nextLine[0] + " skipped");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ecs;
    }

}
