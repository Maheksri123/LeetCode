class Solution {
    public int divide(int dividend, int divisor) {
        // Handle overflow edge case: Integer.MIN_VALUE / -1 = 2^31 (overflows 32-bit signed int)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Handle direct match edge cases
        if (dividend == divisor) {
            return 1;
        }

        // Determine sign of the result
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert numbers to negative to avoid 32-bit integer overflow (since |MIN_VALUE| > MAX_VALUE)
        int nDividend = dividend < 0 ? dividend : -dividend;
        int nDivisor = divisor < 0 ? divisor : -divisor;

        int quotient = 0;

        // Perform bitwise division using powers of 2
        while (nDividend <= nDivisor) {
            int tempDivisor = nDivisor;
            int multiple = 1;

            // Shift left until shifting further exceeds nDividend or causes overflow
            while (tempDivisor >= (Integer.MIN_VALUE >> 1) && nDividend <= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            nDividend -= tempDivisor;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }
}