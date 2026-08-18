class Solution {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int low = 1;
        int high = x / 2;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Cast to long to avoid integer overflow during multiplication
            if ((long) mid * mid <= x) {
                ans = mid;        // Potential answer found, try larger values
                low = mid + 1;
            } else {
                high = mid - 1;   // Value too large, try smaller values
            }
        }

        return ans;
    }
}