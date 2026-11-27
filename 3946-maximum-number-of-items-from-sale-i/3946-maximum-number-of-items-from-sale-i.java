class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1500;
        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        // multiplesAbove[f] = count of items with factor m > f such that f divides m
        int[] multiplesAbove = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            int totalMultiples = 0;
            for (int m = f; m <= maxFactor; m += f) {
                totalMultiples += factorCount[m];
            }
            multiplesAbove[f] = Math.max(0, totalMultiples - 1);
        }

        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int freebies = multiplesAbove[factor];

            if (price > budget) {
                continue;
            }

            // 1. First copy of this item (0/1 transition): adds 1 bought + freebies
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1 + freebies);
            }

            // 2. Extra copies of this item (unbounded transition): adds 1 per copy
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
}