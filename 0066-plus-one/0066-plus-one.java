class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse from the last digit to the first
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No carry, we are done!
            }
            // If digit is 9, it turns into 0 and carry propagates to the left
            digits[i] = 0;
        }

        // If all digits were 9 (e.g., 999 -> 1000)
        int[] result = new int[n + 1];
        result[0] = 1; // Default values for remaining elements are already 0
        return result;
    }
}