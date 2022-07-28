package nishchay.qn2;

/**
 * Driver class for Directory Copy
 *
 * @author Nishchay Naresh
 */
public class Driver {

    public static void main(String[] args) {

        String source = "C:\\D\\FAST\\merge\\source";
        String destination = "C:\\D\\FAST\\merge\\dest1";
        String model = "C:\\D\\FAST\\merge\\model\\common-api\\src";

        DirectoryCopyWithValidator reference = new DirectoryCopyWithValidator();

        reference.dirCopy(source, destination, model);
    }

}
