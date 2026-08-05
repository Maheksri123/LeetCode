class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                // Sign is only valid at the start or immediately after 'e' or 'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == 'e' || c == 'E') {
                // Exponent is valid only if we haven't seen one yet AND we've seen at least one digit
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false; // Must be followed by at least one new digit
            } else if (c == '.') {
                // Dot is valid only if we haven't seen a dot OR an exponent yet
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else {
                // Invalid character
                return false;
            }
        }

        // Must end with at least one digit (either before or after 'e'/'E')
        return seenDigit;
    }
}