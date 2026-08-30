class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIndex = 0, maxIndex = 0;
        for (int k = 0; k < n; k++) {
            if (nums[k] < nums[minIndex]) minIndex = k;
            if (nums[k] > nums[maxIndex]) maxIndex = k;
        }

        int i = Math.min(minIndex, maxIndex);
        int j = Math.max(minIndex, maxIndex);

        int option1 = j + 1;            // Both from front
        int option2 = n - i;            // Both from back
        int option3 = (i + 1) + (n - j); // One from front, one from back

        return Math.min(option1, Math.min(option2, option3));
    }
}