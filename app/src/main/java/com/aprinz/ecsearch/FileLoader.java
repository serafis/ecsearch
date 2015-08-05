package com.aprinz.ecsearch;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Alexander on 27.07.2015.
 */
public class FileLoader {
    private static final String TAG = "FileLoader";

    public static String readFile(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                context.getAssets().open(filename)));

        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            if (++i == 1)
                return line;//only read the first line
            line = reader.readLine();
        }
        reader.close();
        return "Nothing to get";
    }

//    public static String readFileFromDownloadDir(Context context, String filename) {
//        File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//        File file = new File(dir, filename);
//
//        //TODO check if exists
//
//        return readFile(context, file);
//    }

    public static String loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing asset " + name);
                }
            }
        }
        return null;
    }
}
