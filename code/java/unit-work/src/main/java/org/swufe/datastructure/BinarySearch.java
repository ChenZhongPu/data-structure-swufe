package org.swufe.datastructure;
import java.util.List;

public class BinarySearch {
    /**
     * Binary Search
     * @param list a sorted List
     * @return the index of `target` (-1 if not found)
     */
    public int search(List<Integer> list, int target) {
        int high = list.size() - 1;
        int low = 0;
        while (high >= low) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
