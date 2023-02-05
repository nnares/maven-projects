package nishchay.qn2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryCopy {

    public static void main(String[] args) {

        String source = "C:\\D\\FAST\\merge\\source";
        String destination = "C:\\D\\FAST\\merge\\dest1";
        String model = "C:\\D\\FAST\\merge\\model\\common-api\\src";


        Path sourcePath = Paths.get(source);
        Path destPath = Paths.get(destination);
        Path modelPath = Paths.get(model);


        try {
            Files.walk(sourcePath)
                    .filter(file -> file.toString().endsWith(".java"))
                    .forEach(javaFile -> {
                        try {
                            Path targetDirectory = Files.walk(modelPath)
                                    .filter(modelFile -> modelPath.endsWith(javaFile.getFileName())).findFirst()
                                    .map(modelFile -> destPath
                                            .resolve(modelFile.subpath(modelPath.getNameCount(), modelFile.getNameCount()))).get();
                            Files.createDirectories(targetDirectory);
                            //Files. copy(javaFile, targetDirectory.
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}