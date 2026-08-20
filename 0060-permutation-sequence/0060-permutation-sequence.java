import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int fact = 1;

        // Compute (n-1)! and prepare numbers list [1, 2, ..., n]
        for (int i = 1; i < n; i++) {
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);

        // Convert k to 0-based index
        k = k - 1;

        StringBuilder sb = new StringBuilder();

        while (true) {
            // Find current digit
            int index = k / fact;
            sb.append(numbers.get(index));
            numbers.remove(index);

            // Exit when all numbers are selected
            if (numbers.isEmpty()) {
                break;
            }

            // Update k and factorial for the next block
            k = k % fact;
            fact = fact / numbers.size();
        }

        return sb.toString();
    }
}