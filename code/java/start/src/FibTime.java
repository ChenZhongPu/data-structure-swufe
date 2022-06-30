import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FibTime {
    public static void main(String[] args) throws IOException {
        List<Integer> ns = Arrays.asList(20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        Path p = Paths.get("fib_java.txt");
        Files.deleteIfExists(p);
        Fibonacci f = new Fibonacci();
        for (int n : ns) {
            long start = System.currentTimeMillis();
            f.fibonacci(n);
            long end = System.currentTimeMillis();
            Files.writeString(p, String.format("%d  %d\n", n, end - start),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
    }
}
