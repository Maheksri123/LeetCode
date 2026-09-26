import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Store knowledge in a HashMap for O(1) lookup
        Map<String, String> dict = new HashMap<>();
        for (List<String> pair : knowledge) {
            dict.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean inBracket = false;

        // Step 2: Iterate through characters of s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                inBracket = true;
            } else if (c == ')') {
                inBracket = false;
                String key = currentKey.toString();
                result.append(dict.getOrDefault(key, "?"));
                currentKey.setLength(0); // Reset key buffer
            } else {
                if (inBracket) {
                    currentKey.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}