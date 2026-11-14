import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Iterate through all possible starting points of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Expand the subarray length from l to r
            for (int j = i; j < n && (j - i + 1) <= r; j++) {
                currentSum += nums.get(j);
                
                // Check if the length is at least l and sum is positive
                int length = j - i + 1;
                if (length >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }

        // Return the minimum positive sum, or -1 if no valid subarray exists
        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;
    }
}