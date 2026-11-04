
        
import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCost = new long[n];
        Arrays.fill(windowCost, Long.MAX_VALUE);

        // Two TreeMaps to track elements in the sliding window and calculate median cost
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftCount = 0, rightCount = 0;
        long leftSum = 0, rightSum = 0;

        // Target sizes for left and right partitions to easily access median
        int targetLeftSize = (x + 1) / 2;

        for (int i = 0; i < n; i++) {
            // 1. Add current element into the window
            if (left.isEmpty() || nums[i] <= left.lastKey()) {
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
                leftSum += nums[i];
                leftCount++;
            } else {
                right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
                rightSum += nums[i];
                rightCount++;
            }

            // 2. Remove the element that slid out of the window
            if (i >= x) {
                int out = nums[i - x];
                if (left.containsKey(out)) {
                    removeElement(left, out);
                    leftSum -= out;
                    leftCount--;
                } else {
                    removeElement(right, out);
                    rightSum -= out;
                    rightCount--;
                }
            }

            // 3. Balance the balanced partitions
            while (leftCount < targetLeftSize && rightCount > 0) {
                int firstRight = right.firstKey();
                removeElement(right, firstRight);
                rightSum -= firstRight;
                rightCount--;

                left.put(firstRight, left.getOrDefault(firstRight, 0) + 1);
                leftSum += firstRight;
                leftCount++;
            }
            while (leftCount > targetLeftSize) {
                int lastLeft = left.lastKey();
                removeElement(left, lastLeft);
                leftSum -= lastLeft;
                leftCount--;

                right.put(lastLeft, right.getOrDefault(lastLeft, 0) + 1);
                rightSum += lastLeft;
                rightCount++;
            }

            // 4. Calculate cost if a full window of size x is completed
            if (i >= x - 1) {
                long median = left.lastKey();
                long currentCost = (median * leftCount - leftSum) + (rightSum - median * rightCount);
                windowCost[i] = currentCost;
            }
        }

        // 5. Dynamic Programming to find minimum operations for K non-overlapping subarrays
        long[][] dp = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2); // Avoid overflow
        }

        for (int i = 1; i <= k; i++) {
            for (int j = i * x; j <= n; j++) {
                // Option A: Skip current position
                dp[i][j] = dp[i][j - 1];

                // Option B: Take the window ending at index j - 1
                long currentWindowCost = windowCost[j - 1];
                if (currentWindowCost != Long.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - x] + currentWindowCost);
                }
            }
        }

        return dp[k][n];
    }

    private void removeElement(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
    }
}
