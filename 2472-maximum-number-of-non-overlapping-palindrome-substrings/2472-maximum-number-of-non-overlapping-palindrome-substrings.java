class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // Check if s[i - k + 1 ... i] is a palindrome of length k
            if (i - k + 1 >= 0 && isPalindrome(s, i - k + 1, i)) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i - k + 1] + 1);
            }
            // Check if s[i - k ... i] is a palindrome of length k + 1
            if (i - k >= 0 && isPalindrome(s, i - k, i)) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i - k] + 1);
            }
        }

        return dp[n];
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}