from collections import deque


class RecentCounter:
    """Number of Recent Calls from leetcode
    (https://leetcode.com/problems/number-of-recent-calls/)."""

    def __init__(self):
        self.data = deque()

    def ping(self, t: int) -> int:
        cnt = 0
        while len(self.data) > 0 and (t - 3000) > self.data[0]:
            cnt += 1
            self.data.popleft()
        self.data.append(t)
        return len(self.data) - cnt
