package nishchay.qn2;

/**
 * Driver class for Directory Copy
 *
 * @author Nishchay Naresh
 */
public class Driver {

    public static void main(String[] args) {

        String source = "E:\\FAST\\swAG\\merge\\source";
        String destination = "E:\\FAST\\swAG\\merge\\dest1";
        String model = "E:\\FAST\\swAG\\merge\\model\\common-api\\src";

        DirectoryCopyWithValidator reference = new DirectoryCopyWithValidator();

        reference.dirCopy(source, destination, model);
    }

}
