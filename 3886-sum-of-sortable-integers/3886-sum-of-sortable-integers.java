import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        
        // Precompute prefix maximums, suffix minimums, and drop counts for O(1) block validations
        int[] prefMax = new int[n + 1];
        prefMax[0] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }
        
        int[] suffMin = new int[n + 1];
        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }
        
        // Count internal cyclic drops and global order using prefix sums
        int[] drops = new int[n];
        for (int i = 1; i < n; i++) {
            drops[i] = drops[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        }
        
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            
            boolean isSortable = true;
            
            for (int i = 0; i < n; i += k) {
                int start = i;
                int end = i + k - 1;
                
                // 1. Check internal drops within block nums[start ... end]
                // A valid rotatable block can have at most 1 drop internally (including wrap-around)
                int internalDrops = getDrops(drops, start, end);
                if (nums[end] > nums[start]) {
                    internalDrops++;
                }
                
                if (internalDrops > 1) {
                    isSortable = false;
                    break;
                }
                
                // 2. Check boundary condition with adjacent blocks
                if (start > 0 && prefMax[start] > suffMin[start]) {
                    // Check if block minimum >= previous block maximum via sorted check logic
                    // Actually, let's verify block-to-block monotonic ordering:
                    // Max of current block must be <= Min of next block if they appear sequentially,
                    // but since blocks can be rotated, we just check min/max boundaries:
                    // Find actual min and max of this rotated block:
                }
            }
            
            // Re-verifying block boundaries cleanly:
            if (isSortable) {
                if (isValidPartition(nums, n, k)) {
                    totalSum += k;
                }
            }
        }
        
        return totalSum;
    }
    
    private int getDrops(int[] drops, int start, int end) {
        if (start >= end) return 0;
        return drops[end] - drops[start];
    }
    
    private boolean isValidPartition(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i += k) {
            // Find min and max of the current block in O(k)
            int blockMin = Integer.MAX_VALUE;
            int blockMax = Integer.MIN_VALUE;
            
            int drops = 0;
            for (int j = 0; j < k; j++) {
                int curr = nums[i + j];
                blockMin = Math.min(blockMin, curr);
                blockMax = Math.max(blockMax, curr);
                if (j > 0 && nums[i + j - 1] > curr) {
                    drops++;
                }
            }
            // Check wrap-around drop
            if (k > 1 && nums[i + k - 1] > nums[i]) {
                drops++;
            }
            
            // A block is rotatable iff it has <= 1 drop total
            if (drops > 1) return false;
            
            // Blocks must be non-decreasing relative to each other
            if (blockMin < prevMax) return false;
            prevMax = blockMax;
        }
        
        return true;
    }
}