import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countLessThan = 0;
        int countTarget = 0;
        
        // Count elements smaller than target and elements equal to target
        for (int num : nums) {
            if (num < target) {
                countLessThan++;
            } else if (num == target) {
                countTarget++;
            }
        }
        
        // Generate the sorted list of target indices
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < countTarget; i++) {
            result.add(countLessThan + i);
        }
        
        return result;
    }
}