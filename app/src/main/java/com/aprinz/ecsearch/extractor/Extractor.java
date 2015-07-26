package com.aprinz.ecsearch.extractor;

import android.util.Log;

import com.aprinz.ecsearch.ErrorCode;
import com.opencsv.CSVReader;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
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
        try {
            return extract(new CSVReader(new FileReader(file), ';'));
        } catch (FileNotFoundException e) {
            throw new ExtractorException(e);
        }
    }

    public static Set<ErrorCode> extract(String filecontent) {
        return extract(new CSVReader(new CharArrayReader(filecontent.toCharArray()), ';'));
    }

    private static Set<ErrorCode> extract(CSVReader reader) {
        Set<ErrorCode> ecs = new HashSet<ErrorCode>();
        String[] nextLine;
        try {
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
            throw new ExtractorException(e);
        }
        return ecs;
    }

}
