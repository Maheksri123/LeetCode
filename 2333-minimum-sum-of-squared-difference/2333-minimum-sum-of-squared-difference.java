import java.util.HashMap;
import java.util.Map;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long totalK = (long) k1 + k2;
        int n = nums1.length;
        
        // Count frequencies of each difference
        Map<Integer, Long> diffCount = new HashMap<>();
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffCount.put(diff, diffCount.getOrDefault(diff, 0L) + 1L);
            maxDiff = Math.max(maxDiff, diff);
        }
        
        // Greedily reduce from maxDiff down to 1
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            long count = diffCount.getOrDefault(d, 0L);
            if (count == 0) continue;
            
            // Number of reductions we can apply to elements with difference `d`
            long operationsToTake = Math.min(count, totalK);
            
            totalK -= operationsToTake;
            
            // Update counts: decrease count of `d`, increase count of `d - 1`
            diffCount.put(d, count - operationsToTake);
            diffCount.put(d - 1, diffCount.getOrDefault(d - 1, 0L) + operationsToTake);
        }
        
        // Calculate the final minimum sum of squared differences
        long minSumSqDiff = 0;
        for (Map.Entry<Integer, Long> entry : diffCount.entrySet()) {
            long d = entry.getKey();
            long count = entry.getValue();
            minSumSqDiff += count * d * d;
        }
        
        return minSumSqDiff;
    }
}