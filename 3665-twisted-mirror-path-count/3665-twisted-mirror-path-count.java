class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        // dp[i][j][0] -> number of paths arriving/passing through (i, j) moving Right
        // dp[i][j][1] -> number of paths arriving/passing through (i, j) moving Down
        long[][][] dp = new long[m][n][2];

        // Base cases: starting at (0, 0)
        // From (0,0), if we try to move right or down:
        int[] startR = getNext(0, 0, 0, grid, m, n);
        if (startR != null) {
            dp[startR[0]][startR[1]][startR[2]] = (dp[startR[0]][startR[1]][startR[2]] + 1) % MOD;
        }
        
        int[] startD = getNext(0, 0, 1, grid, m, n);
        if (startD != null) {
            dp[startD[0]][startD[1]][startD[2]] = (dp[startD[0]][startD[1]][startD[2]] + 1) % MOD;
        }

        long totalPaths = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int dir = 0; dir < 2; dir++) {
                    long ways = dp[i][j][dir];
                    if (ways == 0) continue;

                    if (i == m - 1 && j == n - 1) {
                        totalPaths = (totalPaths + ways) % MOD;
                        continue;
                    }

                    // Try moving Right (dir = 0) and Down (dir = 1) from current cell
                    for (int nextDir = 0; nextDir < 2; nextDir++) {
                        int[] next = getNext(i, j, nextDir, grid, m, n);
                        if (next != null) {
                            int ni = next[0], nj = next[1], nd = next[2];
                            dp[ni][nj][nd] = (dp[ni][nj][nd] + ways) % MOD;
                        }
                    }
                }
            }
        }

        // Add ways if bottom-right is reached
        return (int) ((dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD);
    }

    // Helper function to simulate reflections recursively or iteratively until a non-mirror or out-of-bounds is hit
    private int[] getNext(int r, int c, int dir, int[][] grid, int m, int n) {
        int nr = r + (dir == 1 ? 1 : 0);
        int nc = c + (dir == 0 ? 1 : 0);

        while (true) {
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                return null; // Out of bounds
            }
            if (grid[nr][nc] == 0) {
                return new int[]{nr, nc, dir};
            }
            // Hit a mirror at (nr, nc)
            // If we entered moving right (dir == 0), we turn down (dir = 1) and move to cell below mirror (nr + 1, nc)
            // If we entered moving down (dir == 1), we turn right (dir = 0) and move to cell right of mirror (nr, nc + 1)
            if (dir == 0) {
                dir = 1;
                nr = nr + 1;
            } else {
                dir = 0;
                nc = nc + 1;
            }
        }
    }
}