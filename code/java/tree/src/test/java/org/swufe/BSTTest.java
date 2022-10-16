package org.swufe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    @Test
    void operation() {
        BST<Integer> bst = new BST<>();
        assertTrue(bst.isEmpty());
        bst.put(12);
        bst.put(5);
        bst.put(2);
        assertEquals(bst.size(), 3);
        bst.put(9);
        bst.put(18);
        bst.put(19);
        bst.put(9);
        bst.put(15);
        bst.put(17);
        bst.put(13);
        //           12
        //         /    \
        //       5      18
        //     /  \    /  \
        //    2    9  15  19
        //            / \
        //          13   17
        assertEquals(bst.size(), 9);
        assertEquals(bst.height(), 3);
        assertTrue(bst.contains(9));
        assertFalse(bst.contains(7));

        List<Integer> keys = bst.range(8, 15);
        assertEquals(keys, Arrays.asList(9, 12, 13, 15));

        assertEquals(bst.min(), 2);
        assertEquals(bst.max(), 19);
        bst.removeMin();
        assertEquals(bst.min(), 5);
        bst.removeMax();
        assertEquals(bst.max(), 18);

        bst.remove(9);
        assertFalse(bst.contains(9));
        assertEquals(bst.size(), 6);
    }
}