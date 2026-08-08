class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int index = 0;
        int n = s.length();

        // 1. Skip leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // If string was only whitespaces
        if (index == n) {
            return 0;
        }

        // 2. Determine sign
        int sign = 1;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }

        // 3. Read numbers and check for overflow/underflow
        long total = 0;
        while (index < n) {
            char digit = s.charAt(index);
            
            // Stop if non-digit character is encountered
            if (digit < '0' || digit > '9') {
                break;
            }

            total = total * 10 + (digit - '0');

            // 4. Handle 32-bit signed integer range limits
            if (sign == 1 && total > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -total < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            index++;
        }

        return (int) (total * sign);
    }
}