class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // If the last seen positions don't match, mapping is invalid
            if (mapS[charS] != mapT[charT]) {
                return false;
            }

            // Store the current position + 1 (1-based index to avoid 0 default value collision)
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }

        return true;
    }
}