class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Use long everywhere to avoid integer overflow
        long c1 = cost1;
        long c2 = cost2;
        long cBoth = costBoth;
        long n1 = need1;
        long n2 = need2;

        // 1. Overlapping requirement that satisfies both type 1 and type 2
        long common = Math.min(n1, n2);
        long totalCost = common * Math.min(cBoth, c1 + c2);

        // 2. Remaining requirement for type 1 only
        long rem1 = n1 - common;
        totalCost += rem1 * Math.min(cBoth, c1);

        // 3. Remaining requirement for type 2 only
        long rem2 = n2 - common;
        totalCost += rem2 * Math.min(cBoth, c2);

        return totalCost;
    }
}