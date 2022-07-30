package org.swufe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Search {
    private static long[] search(int n) {
        List<Integer> list = Stream.generate(new Random()::ints)
                .flatMap(IntStream::boxed)
                .distinct()
                .limit(n).collect(Collectors.toList());
        int size = list.size();

        // save all data in a RBT
        RBT2<Integer> rbt = new RBT2<>();
        for (int i : list) {
            rbt.put(i);
        }

        // search 0, n-1, n/2, n/4, 3n/4, n/8, 7n/8, n/16, 15n/16
        List<Integer> targets = Arrays.asList(0, size-1, size/2, size/4, 3*size/4, size/8, 7*size/8, size/16, 15*size/16);
        long start, end;
        start = System.currentTimeMillis();
        for (int t : targets) {
            list.contains(t);
        }
        end = System.currentTimeMillis();
        long array_time = end - start;

        start = System.currentTimeMillis();
        for (int t : targets) {
            rbt.contains(t);
        }
        end = System.currentTimeMillis();
        long rbt_time = end - start;
        return new long[]{array_time, rbt_time};
    }

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000000, 2000000, 3000000, 4000000, 5000000};

        Path path = Paths.get("search.txt");
        Files.deleteIfExists(path);
        for (int size : sizes) {
            long[] time = search(size);
            Files.writeString(path, String.format("%d   %d  %d\n", size, time[0], time[1]),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
    }
}
