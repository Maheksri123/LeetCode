class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;  // Tracks unmatched '('
        int closeCount = 0; // Tracks unmatched ')'

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--; // Balance with a matching '('
                } else {
                    closeCount++; // Unmatched ')' found
                }
            }
        }

        // Total additions needed = unmatched '(' + unmatched ')'
        return openCount + closeCount;
    }
}