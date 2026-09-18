import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<int[]> validIntervals = new ArrayList<>();

        // Step 2: Extend boundaries for each character
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int left = first[i];
            int right = last[i];
            boolean isValid = true;

            for (int j = left; j <= right; j++) {
                int ch = s.charAt(j) - 'a';
                if (first[ch] < left) { // Expands before starting position 'left', invalid start
                    isValid = false;
                    break;
                }
                right = Math.max(right, last[ch]);
            }

            if (isValid) {
                validIntervals.add(new int[]{left, right});
            }
        }

        // Step 3: Sort valid intervals by end index and greedily select non-overlapping ones
        validIntervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > lastEnd) {
                result.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }

        return result;
    }
}