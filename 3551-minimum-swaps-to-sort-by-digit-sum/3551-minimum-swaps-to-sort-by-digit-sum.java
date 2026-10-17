import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private int getDigitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Pair each number with its digit sum and original index
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices based on digit sum, then by value
        Arrays.sort(indices, (a, b) -> {
            int sumA = getDigitSum(nums[a]);
            int sumB = getDigitSum(nums[b]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        // Map each sorted value to its correct target index
        Map<Integer, Integer> valueToTargetIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToTargetIndex.put(nums[indices[i]], i);
        }

        // Find cycles to calculate minimum swaps
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    // Move to the index where nums[j] should ideally be placed
                    j = valueToTargetIndex.get(nums[j]);
                }
            }
        }

        return n - cycles;
    }
}