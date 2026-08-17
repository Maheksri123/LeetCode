import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1; // 1 for positive, -1 for negative

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // Push the current result and sign onto stack for later
                stack.push(result);
                stack.push(sign);
                // Reset for expression inside parentheses
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                // Pop the sign before parenthesized expression
                result *= stack.pop();
                // Add the result before parenthesized expression
                result += stack.pop();
            }
        }

        // Add the last accumulated number
        if (number != 0) {
            result += sign * number;
        }

        return result;
    }
}