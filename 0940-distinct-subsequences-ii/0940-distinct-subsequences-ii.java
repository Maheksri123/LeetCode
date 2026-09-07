class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long currentSum = 0;
            for (int i = 0; i < 26; i++) {
                currentSum = (currentSum + last[i]) % MOD;
            }
            // Add 1 for the subsequence consisting of just character c
            last[idx] = (currentSum + 1) % MOD;
        }
        
        long total = 0;
        for (long count : last) {
            total = (total + count) % MOD;
        }
        
        return (int) total;
    }
}