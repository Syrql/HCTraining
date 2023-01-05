package fr.syrql.spigot.utils;

import java.io.*;

public class FileUtils {

    public static void createFile(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public static void save(File file, String text) {
        final FileWriter fileWriter;

        try {
            createFile(file);
            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadContent(File file) {
        if (file.exists()) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                final StringBuilder stringBuilder = new StringBuilder();

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bufferedReader.close();

                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
