class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case: prefix sum of 0 is even
        long currentSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;

            // If current prefix sum is odd
            if (currentSum % 2 != 0) {
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } 
            // If current prefix sum is even
            else {
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}