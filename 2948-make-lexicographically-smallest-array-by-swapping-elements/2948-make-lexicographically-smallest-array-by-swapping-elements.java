import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Store pair of (value, original_index)
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }

        // Sort pairs primarily by value
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            List<Integer> values = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();

            // Find connected group where adjacent difference <= limit
            while (j < n) {
                if (j > i && paired[j][0] - paired[j - 1][0] > limit) {
                    break;
                }
                values.add(paired[j][0]);
                indices.add(paired[j][1]);
                j++;
            }

            // Sort original indices so smallest values go into smallest indices
            Collections.sort(indices);

            // Reassign sorted values into sorted target positions
            for (int k = 0; k < values.size(); k++) {
                result[indices.get(k)] = values.get(k);
            }

            i = j;
        }

        return result;
    }
}