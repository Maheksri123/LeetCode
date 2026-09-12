import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalNumbers(int[] digits) {
        // Count frequencies of available digits
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Iterate through all 3-digit even numbers
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;        // Hundreds place
            int d2 = (num / 10) % 10;   // Tens place
            int d3 = num % 10;          // Units place

            // Count digit frequencies needed for the current number
            int[] currentFreq = new int[10];
            currentFreq[d1]++;
            currentFreq[d2]++;
            currentFreq[d3]++;

            // Check if available digits satisfy the required count
            boolean isValid = true;
            for (int i = 0; i < 10; i++) {
                if (currentFreq[i] > freq[i]) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                count++;
            }
        }

        return count;
    }
}