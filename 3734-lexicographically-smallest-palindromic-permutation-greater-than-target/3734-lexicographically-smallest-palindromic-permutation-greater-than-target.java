class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check palindrome validity
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        // Half character frequencies
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int halfLen = n / 2;
        char[] prefix = new char[halfLen];

        // Try to match target prefix of length L from halfLen down to 0
        for (int L = halfLen; L >= 0; L--) {
            int[] avail = halfCount.clone();
            boolean possible = true;

            // Step 1: Fix prefix[0...L-1] to match target[0...L-1]
            for (int i = 0; i < L; i++) {
                int idx = target.charAt(i) - 'a';
                if (avail[idx] <= 0) {
                    possible = false;
                    break;
                }
                avail[idx]--;
                prefix[i] = target.charAt(i);
            }

            if (!possible) continue;

            // If L < halfLen, increment prefix[L] to be strictly greater than target[L]
            if (L < halfLen) {
                int startChar = target.charAt(L) - 'a' + 1;
                int chosen = -1;
                for (int c = startChar; c < 26; c++) {
                    if (avail[c] > 0) {
                        chosen = c;
                        break;
                    }
                }
                if (chosen == -1) continue;

                avail[chosen]--;
                prefix[L] = (char) ('a' + chosen);

                // Fill remaining halfLen - L - 1 with smallest available characters
                int p = L + 1;
                for (int c = 0; c < 26; c++) {
                    while (avail[c] > 0) {
                        prefix[p++] = (char) ('a' + c);
                        avail[c]--;
                    }
                }

                // Construct and return the full palindrome
                return constructResult(prefix, midChar, n);
            } else {
                // L == halfLen: prefix matches target[0...halfLen-1] exactly
                String cand = constructResult(prefix, midChar, n);
                if (cand.compareTo(target) > 0) {
                    return cand;
                }
            }
        }

        return "";
    }

    private String constructResult(char[] prefix, char midChar, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        if (n % 2 != 0) {
            sb.append(midChar);
        }
        for (int i = prefix.length - 1; i >= 0; i--) {
            sb.append(prefix[i]);
        }
        return sb.toString();
    }
}