package com.fasid.utils;

import java.io.*;

/**
 * This Class deals with operations related to File
 */
public class FileUtils {


    public static void deleteFile(String filePath) {

        File file = new File(filePath);

        if (file.exists()) {
            if (file.isDirectory()) {
                String[] files = file.list();
                for (String eachFile : files) {
                    File currentFile = new File(file.getPath(), eachFile);
                    currentFile.delete();
                }
            } else {
                file.delete();
            }

        } else {
            throw new RuntimeException("File not found - check path -" + filePath);
        }

    }

    public void writeToFile(String data, String filePath) {
        BufferedWriter output = null;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                new File(filePath).createNewFile();
            }
            output = new BufferedWriter(new FileWriter(filePath, false));
            output.write(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean isFileDownladed(String downloadPath, String fileName) {
        File dir = new File(downloadPath);

        File[] dirContents = dir.listFiles();

        boolean isDownloaded = false;

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().contains(fileName)) {
                dirContents[i].delete();
                isDownloaded = true;
                break;
            }
        }

        return isDownloaded;
    }

    public static void copyFileToAnotherLocation(String originalLoc, String finalLoc) {
        File originalFile = new File(originalLoc);
        File newFile = new File(finalLoc);
        try {
            org.apache.commons.io.FileUtils.copyFile(originalFile, new File(finalLoc));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String readFromAFile(String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }

        }

        byte[] expectedDataToString = stringBuilder.toString().getBytes("UTF-8");

        return new String(expectedDataToString, "UTF-8");
    }


}
