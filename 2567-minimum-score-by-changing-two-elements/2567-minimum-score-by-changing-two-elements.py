class Solution(object):
    def minimizeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums.sort()
        n = len(nums)

        # Case 1: Change the two smallest elements -> new range is nums[n-1] - nums[2]
        # Case 2: Change the two largest elements  -> new range is nums[n-3] - nums[0]
        # Case 3: Change smallest & largest        -> new range is nums[n-2] - nums[1]
        return min(
            nums[n - 1] - nums[2],
            nums[n - 3] - nums[0],
            nums[n - 2] - nums[1],
        )