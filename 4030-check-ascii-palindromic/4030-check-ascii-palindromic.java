class Solution {
    public boolean isPalindromic(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            sb.append(binary);
        }
        
        String fullBinary = sb.toString();
        int left = 0;
        int right = fullBinary.length() - 1;
        
        while (left < right) {
            if (fullBinary.charAt(left) != fullBinary.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}