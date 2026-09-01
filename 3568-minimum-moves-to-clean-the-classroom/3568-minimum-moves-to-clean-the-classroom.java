import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int totalLitter = litters.size();
        if (totalLitter == 0) return 0;
        
        // Assign each litter cell an index to create a bitmask
        int[][] litterIndex = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }
        for (int i = 0; i < totalLitter; i++) {
            int[] pos = litters.get(i);
            litterIndex[pos[0]][pos[1]] = i;
        }
        
        int targetMask = (1 << totalLitter) - 1;
        
        // bestEnergy[r][c][mask] stores max energy achieved at (r, c) with mask
        int[][][] bestEnergy = new int[m][n][1 << totalLitter];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                for (int mask = 0; mask < (1 << totalLitter); mask++) {
                    bestEnergy[r][c][mask] = -1;
                }
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        // State format: {r, c, mask, currentEnergy}
        queue.offer(new int[]{startR, startC, 0, energy});
        bestEnergy[startR][startC][0] = energy;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int currE = curr[3];
                
                if (mask == targetMask) {
                    return steps;
                }
                
                if (currE == 0) continue;
                
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') continue;
                    
                    int nextE = currE - 1;
                    int nextMask = mask;
                    
                    if (cell == 'R') {
                        nextE = energy;
                    } else if (cell == 'L') {
                        int idx = litterIndex[nr][nc];
                        if (idx != -1) {
                            nextMask |= (1 << idx);
                        }
                    }
                    
                    if (nextE > bestEnergy[nr][nc][nextMask]) {
                        bestEnergy[nr][nc][nextMask] = nextE;
                        queue.offer(new int[]{nr, nc, nextMask, nextE});
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}