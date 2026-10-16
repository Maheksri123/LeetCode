class Solution {
    private static final int MOD = 1_000_000_007;

    long[] sumSq;
    long[] sumVal;
    long[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sumSq = new long[4 * n];
        sumVal = new long[4 * n];
        lazy = new long[4 * n];

        // Keeps track of the last seen index for each element value
        int[] lastPos = new int[100005];
        java.util.Arrays.fill(lastPos, -1);

        long totalSum = 0;
        long currentTotalSquares = 0;

        for (int j = 0; j < n; j++) {
            int val = nums[j];
            int prev = lastPos[val];
            int L = prev + 1; // Range [L, j] gets its distinct count increased by 1
            int R = j;

            // 1. Update segment tree for the range [L, j] by adding 1 to distinct counts
            if (L <= R) {
                update(1, 0, n - 1, L, R, 1);
            }
            lastPos[val] = j;

            // 2. The sum of squares of all subarrays ending at index j is the root of our segment tree (range [0, j])
            currentTotalSquares = querySumSq(1, 0, n - 1, 0, j);
            totalSum = (totalSum + currentTotalSquares) % MOD;
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // sumSq' = sum( (x + val)^2 ) = sum(x^2) + 2*val*sum(x) + val^2 * len
        sumSq[node] = (sumSq[node] + 2 * val * sumVal[node] + val * val * len) % MOD;
        // sumVal' = sum(x + val) = sum(x) + val * len
        sumVal[node] = (sumVal[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumVal[node] = (sumVal[2 * node] + sumVal[2 * node + 1]) % MOD;
    }

    private long querySumSq(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return sumSq[node];
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        long res = 0;
        if (ql <= mid) {
            res = (res + querySumSq(2 * node, l, mid, ql, qr)) % MOD;
        }
        if (qr > mid) {
            res = (res + querySumSq(2 * node + 1, mid + 1, r, ql, qr)) % MOD;
        }
        return res;
    }
}