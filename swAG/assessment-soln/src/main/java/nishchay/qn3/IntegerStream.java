package nishchay.qn3;

import nishchay.qn3.statistics.Statistic;

import java.util.LinkedList;
import java.util.List;

/**
 * IntegerStream class - providing implementation for Statistic interface for a stream of Integer
 *
 * @author Nishchay Naresh
 *
 * Soln1 - keep to write place under synchronisation, let the reader have lateste value using volatile
 * Soln2 - use the lock api to guard at all the read & write place - but in this case we will get less performance
 * soln3 -
 */
public class IntegerStream implements Statistic {

    private List<Integer> data;

    private int sum;
    private int n;
    private int min, max;
    private float mean;

    public IntegerStream() {

        data = new LinkedList<>();

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        sum = n = 0;
        mean = 0.00f;
    }


    @Override
    public void event(int value) {

        synchronized (this) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }

            data.add(value);
            n++;
            sum = sum + value;
            mean = (float) sum / n;
        }
    }

    @Override
    public float mean() {
        return mean;
    }

    @Override
    public int minimum() {
        return min;
    }

    @Override
    public int maximum() {
        return max;
    }

    @Override
    public float variance() {
        double summation = data.stream()
                .mapToDouble(x -> (x - mean) * (x - mean))
                .sum();
        return (float) summation / n + 1;
    }


}