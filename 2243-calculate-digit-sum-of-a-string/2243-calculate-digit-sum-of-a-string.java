class Solution {
    public String digitSum(String s, int k) {
        // Continue rounds as long as the length of s is greater than k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Divide s into consecutive groups of size k
            for (int i = 0; i < s.length(); i += k) {
                int end = Math.min(i + k, s.length());
                String group = s.substring(i, end);
                
                // Calculate the sum of digits in the current group
                int sum = 0;
                for (int j = 0; j < group.length(); j++) {
                    sum += group.charAt(j) - '0';
                }
                
                // Append the sum as a string to the next round builder
                nextRound.append(sum);
            }
            
            // Update s with the newly merged string
            s = nextRound.toString();
        }
        
        return s;
    }
}