class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n]; // minLen[i] = min length of subarray sum==target found in arr[0...i]
        
        int INF = Integer.MAX_VALUE / 2;
        int left = 0, currentSum = 0;
        int minTotalSum = INF;
        int currentMinLen = INF;

        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            // Shrink window if sum exceeds target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }

            // Valid subarray found
            if (currentSum == target) {
                int currLen = right - left + 1;

                // Check if a valid non-overlapping subarray exists before 'left'
                if (left > 0 && minLen[left - 1] != INF) {
                    minTotalSum = Math.min(minTotalSum, currLen + minLen[left - 1]);
                }

                currentMinLen = Math.min(currentMinLen, currLen);
            }

            // Store the best length seen up to current index 'right'
            minLen[right] = currentMinLen;
        }

        return minTotalSum >= INF ? -1 : minTotalSum;
    }
}