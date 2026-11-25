import java.util.*;

class Solution {
    private static final long BASE1 = 100003L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 100019L;
    private static final long MOD2 = 1_000_000_009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        long[] pref1 = new long[n + 1];
        long[] pref2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            long val = nums[i];
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;
            pref1[i + 1] = (pref1[i] * BASE1 + val) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE2 + val) % MOD2;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(mid, n, pow1, pow2, pref1, pref2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueOfLength(
            int len,
            int n,
            long[] pow1,
            long[] pow2,
            long[] pref1,
            long[] pref2
    ) {
        Map<Long, Integer> freq = new HashMap<>();
        long p1 = pow1[len];
        long p2 = pow2[len];

        for (int start = 0; start + len <= n; start++) {
            int end = start + len;

            long h1 = (pref1[end] - (pref1[start] * p1) % MOD1 + MOD1) % MOD1;
            long h2 = (pref2[end] - (pref2[start] * p2) % MOD2 + MOD2) % MOD2;

            // Combine two 31-bit-range hashes into a single 62-bit key
            long key = (h1 << 31) ^ h2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}