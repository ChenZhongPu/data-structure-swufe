package org.swufe;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class KDTreeTest {

    @Test
    void testRange() {
        List<Point> points = new ArrayList<>(Arrays.asList(new Point(7, 2), new Point(5, 4),
                new Point(9, 6), new Point(4, 7), new Point(8, 1),
                new Point(2, 3)));
        KDTree kdTree = new KDTree();
        kdTree.insert(points);

        List<Point> result = kdTree.range(new Point(0, 0), new Point(6, 6));

        result.sort(Comparator.comparing(Point::getX).thenComparing(Point::getY));
        List<Point> expect = new ArrayList<>(Arrays.asList(new Point(2, 3),
                new Point(5, 4)));

        assertEquals(result, expect);
    }
}