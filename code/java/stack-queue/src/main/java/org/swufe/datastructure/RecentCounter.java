package org.swufe.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class RecentCounter {
    private final Deque<Integer> data;

    public RecentCounter() {
        data = new ArrayDeque<>();
    }

    public int ping(int t) {
        while (!data.isEmpty() && (t - 3000) > data.peekFirst()) {
            data.pollFirst();
        }
        data.offerLast(t);
        return data.size();
    }
}
