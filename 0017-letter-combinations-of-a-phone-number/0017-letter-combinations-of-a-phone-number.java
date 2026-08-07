import java.util.ArrayList;
import java.util.List;

class Solution {
    // Mapping from digit index to phone keypad letters
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        
        // Edge case: empty input string
        if (digits == null || digits.length() == 0) {
            return combinations;
        }

        backtrack(combinations, digits, 0, new StringBuilder());
        return combinations;
    }

    private void backtrack(List<String> combinations, String digits, int index, StringBuilder current) {
        // Base Case: complete combination formed
        if (index == digits.length()) {
            combinations.add(current.toString());
            return;
        }

        // Get matching letters for the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];
        
        for (char c : letters.toCharArray()) {
            current.append(c);                                 // Choose
            backtrack(combinations, digits, index + 1, current); // Explore
            current.deleteCharAt(current.length() - 1);        // Un-choose (Backtrack)
        }
    }
}