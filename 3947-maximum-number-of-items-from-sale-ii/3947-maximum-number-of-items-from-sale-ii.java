import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        long remBudget = budget;
        long minPrice = Long.MAX_VALUE;
        int maxFactor = 0;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // multiples[f] = total count of items whose factor is a multiple of f (including f itself)
        int[] multiples = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= maxFactor; m += f) {
                multiples[f] += freq[m];
            }
        }

        // Collect (price, boostedCount) pairs where price <= 2 * minPrice
        // Using TreeMap to process prices in ascending order and merge equal prices
        TreeMap<Long, Long> boostedByPrice = new TreeMap<>();
        for (int[] item : items) {
            int f = item[0];
            long price = item[1];
            int gain = multiples[f] - 1; // exclude item itself (i != j)
            if (gain > 0 && price <= 2 * minPrice) {
                boostedByPrice.merge(price, (long) gain, Long::sum);
            }
        }

        long totalItems = 0;
        for (Map.Entry<Long, Long> entry : boostedByPrice.entrySet()) {
            long price = entry.getKey();
            long count = entry.getValue();
            if (remBudget < price) break;

            long buy = Math.min(count, remBudget / price);
            totalItems += 2 * buy;
            remBudget -= buy * price;
        }

        // Spend remaining budget on the cheapest item (1 item per copy)
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}