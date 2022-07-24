package org.swufe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BSTHeight {
    public static int height(int n) {
        BST<Integer> bst = new BST<>();
        List<Integer> list = Stream.generate(new Random()::ints)
                .flatMap(IntStream::boxed)
                .distinct()
                .limit(n).collect(Collectors.toList());
        for (int i : list) {
            bst.put(i);
        }
        return bst.height();
    }

    public static void main(String[] args) throws IOException {
        List<Integer> sizes = Arrays.asList(10, 100, 1000, 10000, 100000, 1000000, 10000000);
        Path path = Paths.get("bst_height.txt");
        Files.deleteIfExists(path);
        for (int size : sizes) {
            int height = height(size);
            Files.writeString(path, String.format("%d   %d\n", size, height),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
    }

}
