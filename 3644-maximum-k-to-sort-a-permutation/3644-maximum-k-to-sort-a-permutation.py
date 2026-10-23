class Solution(object):
    def sortPermutation(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        ans = -1  # Start with all bits set to 1 (in two's complement)
        
        for i, x in enumerate(nums):
            if i != x:  # If the element is misplaced
                if ans == -1:
                    ans = x
                else:
                    ans &= x
                    
        # If no elements were misplaced, the array is already sorted, return 0
        return max(ans, 0)