package org.swufe;

import java.util.*;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Integer getItem(int i) {
        assert i == 0 || i == 1;
        if (i == 0) return x;
        else return y;
    }

    public boolean isContained(Point lower, Point upper) {
        return x >= lower.x && x <= upper.x && y >= lower.y && y <= upper.y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class KDTree {

    private static class Node {
        private Point location;

        private int depth;
        private Node left;
        private Node right;

        public Node(Point location, int depth, Node left, Node right) {
            this.location = location;
            this.depth = depth;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    public KDTree() {
        root = null;
        size = 0;
    }

    public void insert(List<Point> p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Point> range(Point lower, Point upper) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 2000; j++) {
                points.add(new Point(i, j));
            }
        }

        Point lower = new Point(500, 500);
        Point upper = new Point(504, 504);
        // naive method
        double start = System.currentTimeMillis();
        List<Point> result1 = points.stream().filter(p -> p.isContained(lower, upper)).toList();
        double end = System.currentTimeMillis();
        System.out.printf("Naive method: %fms\n", end - start);

        KDTree kdTree = new KDTree();
        kdTree.insert(points);
        // kd tree
        start = System.currentTimeMillis();
        List<Point> result2 = kdTree.range(lower, upper);
        end = System.currentTimeMillis();
        System.out.printf("kd tree: %fms\n", end - start);
    }
}
