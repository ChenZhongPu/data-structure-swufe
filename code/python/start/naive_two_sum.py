class Solution:
    def two_sum(self, nums, target):
        for i, v in enumerate(nums):
            for j in range(i + 1, len(nums)):
                if v + nums[j] == target:
                    return [i, j]
        return []


if __name__ == '__main__':
    solution = Solution()
    assert solution.two_sum([2, 7, 11, 15], 9) == [0, 1]
    assert solution.two_sum([3, 2, 4], 6) == [1, 2]
    assert solution.two_sum([3, 3], 6) == [0, 1]
    assert solution.two_sum([3, 3], 7) == []
