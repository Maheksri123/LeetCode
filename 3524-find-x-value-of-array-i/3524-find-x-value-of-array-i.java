class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        // dp[r] stores the number of subarrays ending at the current element whose product mod k is equal to r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;

            // 1. Start a new subarray consisting solely of `num`
            newDp[numMod]++;

            // 2. Extend all existing subarrays ending at the previous element
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newMod = (int) ((1L * r * numMod) % k);
                    newDp[newMod] += dp[r];
                }
            }

            // 3. Accumulate total subarray counts into result
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}