import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] combination;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Step 1: Precompute Pascal's Triangle for combinations C(n, k)
        combination = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            combination[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                combination[i][j] = (combination[i - 1][j - 1] + combination[i - 1][j]) % MOD;
            }
        }

        // Step 2: Convert array to List for easier sub-list operations
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 because the problem asks for ways to *reorder* (excluding the original array itself)
        return (int) (dfs(list) - 1 + MOD) % MOD;
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partition elements into left and right subtrees
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int leftLen = left.size();
        int rightLen = right.size();

        // Ways to interleave left and right elements: C(leftLen + rightLen, leftLen)
        long ways = combination[leftLen + rightLen][leftLen];

        // Multiply by the combinations of subtrees and apply modulo
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}