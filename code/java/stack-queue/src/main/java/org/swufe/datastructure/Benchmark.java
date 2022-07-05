package org.swufe.datastructure;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Benchmark {
    private static long timeCQ(int n) {
        CircularArrayQueue<Integer> q = new CircularArrayQueue<>();
        for (int i = 0; i < n; i++) {
            q.enqueue(ThreadLocalRandom.current().nextInt(n));
        }
        long start = System.currentTimeMillis();
        // to avoid the randomness of single operation
        // we repeat it 20 times
        for (int i = 0; i < 20; i++) {
            q.dequeue();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void timeDequeue() {
        List<Integer> sizes = Arrays.asList(10000000, 20000000, 30000000, 40000000, 50000000);
        for (int size : sizes) {
            long t = timeCQ(size);
            System.out.println(size + ": " + t);
        }
    }

    public static void main(String[] args) {
        timeDequeue();
    }
}
