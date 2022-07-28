package nishchay.qn2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryCopyWithValidatorTest {

    @Test
    void dirCopy() {

        String source = "E:\\FAST\\swAG\\merge\\source";
        String destination = "E:\\FAST\\swAG\\merge\\dest1";
        String model = "E:\\FAST\\swAG\\merge\\model\\common-api\\src";

        DirectoryCopyWithValidator reference = new DirectoryCopyWithValidator();

        reference.dirCopy(source, destination, model);

        List<File> sFiles = new ArrayList<>();
        reference.listFiles(source, sFiles);

        List<File> dFiles = new ArrayList<>();
        reference.listFiles(source, dFiles);

        List<File> mFiles = new ArrayList<>();
        reference.listFiles(source, mFiles);

        Random random = new Random();
        int randomIndex = random.nextInt(dFiles.size());

        File randomFile = dFiles.get(randomIndex);
        assertTrue(mFiles.contains(randomFile) && sFiles.contains(randomFile));


    }
}