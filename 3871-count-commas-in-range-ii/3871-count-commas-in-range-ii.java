class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long start = 1000;
        
        while (n >= start) {
            ans += (n - start + 1);
            
            // Prevent potential overflow when multiplying by 1000
            if (start > Long.MAX_VALUE / 1000) {
                break;
            }
            start *= 1000;
        }
        
        return ans;
    }
}