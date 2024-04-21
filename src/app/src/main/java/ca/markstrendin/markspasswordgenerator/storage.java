package ca.markstrendin.markspasswordgenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class storage {

    public static final String KEYFILE_NAME = "MPwdGen";

    public static void saveKey(Context fileContext, String theKey) throws IOException {

        String saveThisKey;

        if (theKey.length() > 1) {
            saveThisKey = theKey;
        } else {
            saveThisKey = crypto.genRandomKey();
        }

        FileOutputStream fos = fileContext.getApplicationContext().openFileOutput(KEYFILE_NAME, Context.MODE_PRIVATE);
        fos.write(saveThisKey.getBytes());
        fos.close();
    }

    public static boolean checkKey(Context fileContext) {
        try {
            FileInputStream fos = fileContext.getApplicationContext().openFileInput(KEYFILE_NAME);
            fos.close();
            return true;
        } catch (FileNotFoundException ex) {
            // Attempt to make the file
            try {
                saveKey(fileContext,"");
                return true;
            } catch (Exception ex2) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static String getKey(Context fileContext) throws IOException {
        FileInputStream fos = fileContext.getApplicationContext().openFileInput(KEYFILE_NAME);

        int ch;
        StringBuffer returnMe = new StringBuffer("");
        while ((ch = fos.read()) != -1) {
            returnMe.append((char)ch);
        }
        fos.close();
        return returnMe.toString();
    }

}
