import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squares[i] = val * val;
        }
        
        // Sort the squares in ascending order
        Arrays.sort(squares);
        
        long score = 0;
        int mid = n / 2;
        
        // Subtract the smaller half of the squares
        for (int i = 0; i < mid; i++) {
            score -= squares[i];
        }
        
        // Add the larger half of the squares
        for (int i = mid; i < n; i++) {
            score += squares[i];
        }
        
        return score;
    }
}