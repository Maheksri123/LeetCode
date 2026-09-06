class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[j] stores the number of subsequences of s that match t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty string t can always be formed 1 way
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            char charS = s.charAt(i);
            // Iterate backwards to use previous state values safely
            for (int j = n; j >= 1; j--) {
                if (charS == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}