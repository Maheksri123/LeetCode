class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        
        // Compute C(n + k - 1, 2 * k) % MOD
        int N = n + k - 1;
        int R = 2 * k;
        
        if (N < R) return 0;
        
        long num = 1;
        long den = 1;
        
        for (int i = 1; i <= R; i++) {
            num = (num * (N - i + 1)) % MOD;
            den = (den * i) % MOD;
        }
        
        // Modular inverse using Fermat's Little Theorem
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }
    
    private long modInverse(long base, long mod) {
        return power(base, mod - 2, mod);
    }
    
    private long power(long x, long y, long mod) {
        long res = 1;
        x = x % mod;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % mod;
            y >>= 1;
            x = (x * x) % mod;
        }
        return res;
    }
}