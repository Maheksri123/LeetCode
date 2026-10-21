class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Helper to check if string is sorted
        if (isSorted(s)) {
            return 0;
        }
        
        // Strings of length 2 that are not sorted cannot be fixed
        if (n == 2) {
            return -1;
        }
        
        char[] arr = s.toCharArray();
        
        // Find min and max characters and their positions
        char minChar = arr[0], maxChar = arr[0];
        for (char c : arr) {
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // Check if 1 operation is possible
        // 1. Minimum character is at the beginning (proper suffix can be sorted)
        // 2. Maximum character is at the end (proper prefix can be sorted)
        if (arr[0] == minChar || arr[n - 1] == maxChar) {
            return 1;
        }
        
        // Check if 3 operations are needed:
        // When the max character is uniquely at index 0 and min character is uniquely at index n-1
        boolean uniqueMaxAtStart = (arr[0] == maxChar);
        for (int i = 1; i < n; i++) {
            if (arr[i] == maxChar) {
                uniqueMaxAtStart = false;
                break;
            }
        }
        
        boolean uniqueMinAtEnd = (arr[n - 1] == minChar);
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == minChar) {
                uniqueMinAtEnd = false;
                break;
            }
        }
        
        if (uniqueMaxAtStart && uniqueMinAtEnd) {
            return 3;
        }
        
        // Otherwise, 2 operations are sufficient
        return 2;
    }
    
    private boolean isSorted(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}