class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int needed = 0; // Tracks the number of ')' needed

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If needed is odd, it means we have a single ')' waiting for a pair.
                // We must insert 1 ')' to complete it.
                if (needed % 2 != 0) {
                    insertions++;
                    needed--;
                }
                needed += 2;
            } else { // c == ')'
                needed--;
                // If needed becomes -1, we encountered a ')' without a matching '('
                if (needed < 0) {
                    insertions++; // Insert '('
                    needed += 2;  // Now the new '(' needs two ')', but we already have one
                }
            }
        }

        return insertions + needed;
    }
}