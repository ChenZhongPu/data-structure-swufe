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

class Rectangle {
    private Point lower;
    private Point upper;

    public Rectangle(Point lower, Point upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public Point getLower() {
        return lower;
    }

    public Point getUpper() {
        return upper;
    }

    public boolean isContain(Point p) {
        return this.lower.getX() <= p.getX() && this.upper.getX() >= p.getX() &&
                this.lower.getY() <= p.getY() && this.upper.getY() >= p.getY();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "lower=" + lower +
                ", upper=" + upper +
                '}';
    }
}

public class KDTree {

    private static class Node {
        private Point location;
        private Node left;
        private Node right;

        public Node(Point location, Node left, Node right) {
            this.location = location;
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

    public List<Point> range(Rectangle rectangle) {
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
        Rectangle rectangle = new Rectangle(lower, upper);
        // naive method
        double start = System.currentTimeMillis();
        List<Point> result1 = points.stream().filter(rectangle::isContain).toList();
        double end = System.currentTimeMillis();
        System.out.printf("Naive method: %fms\n", end - start);

        KDTree kdTree = new KDTree();
        kdTree.insert(points);
        // kd tree
        start = System.currentTimeMillis();
        List<Point> result2 = kdTree.range(rectangle);
        end = System.currentTimeMillis();
        System.out.printf("kd tree: %fms\n", end - start);
    }
}
