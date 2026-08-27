import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), 0, result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int startIdx, 
                           List<Integer> currentComb, int currentSum, 
                           List<List<Integer>> result) {
        // Base Case: found a valid combination
        if (currentSum == target) {
            result.add(new ArrayList<>(currentComb));
            return;
        }

        // Base Case: sum exceeded target
        if (currentSum > target) {
            return;
        }

        // Explore choices from startIdx onward to avoid duplicate combinations
        for (int i = startIdx; i < candidates.length; i++) {
            currentComb.add(candidates[i]);
            // Pass 'i' instead of 'i + 1' to allow reusing the same candidate
            backtrack(candidates, target, i, currentComb, currentSum + candidates[i], result);
            currentComb.remove(currentComb.size() - 1); // Backtrack
        }
    }
}