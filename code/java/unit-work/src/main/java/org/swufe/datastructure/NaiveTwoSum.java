package org.swufe.datastructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NaiveTwoSum {
    public List<Integer> twoSum(List<Integer> list, int target) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == target) {
                    return Arrays.asList(i, j);
                }
            }
        }
        return Collections.emptyList();
    }
}
