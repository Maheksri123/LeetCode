import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // Store coordinates of 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) list1.add(new int[]{r, c});
                if (img2[r][c] == 1) list2.add(new int[]{r, c});
            }
        }

        // Map to count the occurrences of each (dx, dy) translation vector
        Map<String, Integer> countMap = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                
                String key = dx + "," + dy;
                int count = countMap.getOrDefault(key, 0) + 1;
                countMap.put(key, count);
                
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}