package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.os.Environment;

import java.io.File;

public class FileLocalUtils {
    static final String FILE_NAME = "linhai";
    private static final String ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + FILE_NAME;

    private static boolean isMounted() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static File getRootDir() {
        File file = null;
        if (isMounted()) {
            file = new File(ROOT_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }

    public static File getTempDir() {
        File file = null;
        if (isMounted()) {
            file = new File(ROOT_DIR, "temp");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }

    public static File getLogDir() {
        File file = null;
        if (isMounted()) {
            file = new File(ROOT_DIR, "log");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }

    public static File getSaveDir() {
        File file = null;
        if (isMounted()) {
            file = new File(ROOT_DIR, "download");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }
}
