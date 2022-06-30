public class Fibonacci {
    public long fibonacci(int n) {
        assert n >= 0;
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        long start = System.currentTimeMillis();
        f.fibonacci(30);
        long end = System.currentTimeMillis();
        long elapse = end - start;
        System.out.printf("it runs in %s ms", elapse);
    }
}
