import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<List<Set<String>>> stack = new Stack<>();
        List<Set<String>> currentScope = new ArrayList<>();
        
        Set<String> res = new TreeSet<>();
        Set<String> cur = new TreeSet<>();
        cur.add("");
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '{') {
                // Save current scope to stack
                List<Set<String>> scope = new ArrayList<>();
                scope.add(res);
                scope.add(cur);
                stack.push(scope);
                
                // Reset for the inner group
                res = new TreeSet<>();
                cur = new TreeSet<>();
                cur.add("");
            } else if (c == '}') {
                // Combine cur into res for the inner group
                res.addAll(cur);
                
                // Pop outer scope
                List<Set<String>> scope = stack.pop();
                Set<String> prevRes = scope.get(0);
                Set<String> prevCur = scope.get(1);
                
                // Concatenate the inner evaluated set (res) to outer cur
                cur = product(prevCur, res);
                res = prevRes;
            } else if (c == ',') {
                // Union operation: move cur to res and start new term
                res.addAll(cur);
                cur = new TreeSet<>();
                cur.add("");
            } else {
                // Character: concatenate to current group
                Set<String> charSet = new HashSet<>();
                charSet.add(String.valueOf(c));
                cur = product(cur, charSet);
            }
        }
        
        // Final union
        res.addAll(cur);
        return new ArrayList<>(res);
    }
    
    // Helper method to compute Cartesian Product of two sets of strings
    private Set<String> product(Set<String> set1, Set<String> set2) {
        Set<String> result = new TreeSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
}