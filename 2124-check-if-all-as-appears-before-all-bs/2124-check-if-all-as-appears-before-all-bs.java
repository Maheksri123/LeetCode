class Solution {
    public boolean checkString(String s) {
        boolean seenB = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'b') {
                seenB = true;
            } else if (seenB) {
                // 'a' appeared after a 'b'
                return false;
            }
        }
        
        return true;
    }
}