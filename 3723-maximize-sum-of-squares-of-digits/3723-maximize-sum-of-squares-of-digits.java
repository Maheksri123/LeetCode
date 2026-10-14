class Solution {
    public String maxSumOfSquares(int num, int sum) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            sb.append(digit);
            sum -= digit;
        }
        
        // If there is still remaining sum left over, it's impossible
        if (sum > 0) {
            return "";
        }
        
        return sb.toString();
    }
}