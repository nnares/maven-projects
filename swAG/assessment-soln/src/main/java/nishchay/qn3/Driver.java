package nishchay.qn3;


/**
 * Driver class for IntegerStream functionality
 *
 * @author Nishchay Naresh
 */
public class Driver {

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {

        IntegerStream ref = new IntegerStream();

        ref.event(5);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(8);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(1);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(4);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(2);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(8);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(5);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

        ref.event(5);
        System.out.printf("Stats [ mean = %.2f, min = %d, max = %d, variance=%.2f ]%n", ref.mean(), ref.minimum(), ref.maximum(), ref.variance());

    }

}
