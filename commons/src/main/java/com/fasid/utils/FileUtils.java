package com.fasid.utils;

import java.io.*;

/**
 * This Class deals with operations related to File
 */
public class FileUtils {

    public static void deleteFile(final String filePath) {

        final File file = new File(filePath);

        if (file.exists()) {
            if (file.isDirectory()) {
                final String[] files = file.list();
                for (String eachFile : files) {
                    final File currentFile = new File(file.getPath(), eachFile);
                    currentFile.delete();
                }
            } else {
                file.delete();
            }

        } else {
            throw new RuntimeException("File not found - check path -" + filePath);
        }

    }

    public void writeToFile(final String data, final String filePath) {
        BufferedWriter output = null;

        try {
            final File file = new File(filePath);
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

    public boolean isFileDownladed(final String downloadPath, final String fileName) {
        final File dir = new File(downloadPath);

        final File[] dirContents = dir.listFiles();

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

    public static void copyFileToAnotherLocation(final String originalLoc, final String finalLoc) {
        final File originalFile = new File(originalLoc);
        final File newFile = new File(finalLoc);
        try {
            org.apache.commons.io.FileUtils.copyFile(originalFile, new File(finalLoc));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFromAFile(final String fileName) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }

        }

        final byte[] expectedDataToString = stringBuilder.toString().getBytes("UTF-8");

        return new String(expectedDataToString, "UTF-8");
    }

    private FileUtils() {
        //utility files should not be instantiated and should be by default private
    }

}
