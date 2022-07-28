package nishchay.qn2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Directory Copy With Validator dir
 *
 * @author Nishchay Naresh
 */
public class DirectoryCopyWithValidator {

    /**
     * copying all files from source directory to target directory, provided which all files are preset at validator directory
     *
     * @param source    source directory path
     * @param target    target directory path
     * @param validator validator directory path
     */
    void dirCopy(String source, String target, String validator) {

        // Collecting validator dir filename in a List<String>
        List<String> fileNames = new ArrayList<>();
        listFileNames(validator, fileNames);

        List<String> modelDirFiles = fileNames.stream()
                .map(s -> s.substring(validator.length()))
                .collect(Collectors.toList());

        System.out.printf("File count in validator dir = %d%n", modelDirFiles.size());

        // Listing Files from source dir in a List<File>
        List<File> files = new ArrayList<>();
        listFiles(source, files);

        String name, trimName, targetFileName;
        int conut = 0;
        // File copy & past from source to target
        for (File currFile : files) {
            name = currFile.getAbsolutePath();
            trimName = name.substring(source.length());
            targetFileName = target + trimName;
            File dFile = new File(targetFileName);

            if (currFile.isDirectory()) {
                if (!dFile.exists())
                    dFile.mkdirs();
            } else {
                if (modelDirFiles.contains(trimName)) {
                    System.out.printf("Copying %s to %s%n", currFile.toPath(), dFile.toPath());
                    fileCopy(currFile.toPath(), dFile.toPath());
                    conut++;
                }
            }
        }
        System.out.printf("File copied count = %d%n", conut);
    }


    /**
     * copying all the Files in files from directoryName and its sub-directory recursively
     *
     * @param directoryName source directory name
     * @param files         List<File> to hold all the file
     */
    public void listFiles(String directoryName, List<File> files) {

        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                files.add(file);
                if (file.isDirectory()) {
                    listFiles(file.getAbsolutePath(), files);
                }
            }
    }

    /**
     * copying all fileNames in fileNames from directoryName and its sub-directory recursively
     *
     * @param directoryName source directory name
     * @param fileNames     List<String> to hold all the file
     */
    private void listFileNames(String directoryName, List<String> fileNames) {

        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    fileNames.add(file.getAbsolutePath());
                } else if (file.isDirectory()) {
                    listFileNames(file.getAbsolutePath(), fileNames);
                }
            }
    }

    /**
     * File copy using java nio
     *
     * @param source source file path
     * @param target target file path
     */
    private static void fileCopy(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            System.out.format("I/O error: %s%n", ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

}
