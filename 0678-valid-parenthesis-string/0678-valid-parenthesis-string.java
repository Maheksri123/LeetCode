class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else if (c == '*') {
                low--;  // if '*' is treated as ')'
                high++; // if '*' is treated as '('
            }

            // If high < 0, there are too many ')' characters
            if (high < 0) {
                return false;
            }

            // low cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        return low == 0;
    }
}