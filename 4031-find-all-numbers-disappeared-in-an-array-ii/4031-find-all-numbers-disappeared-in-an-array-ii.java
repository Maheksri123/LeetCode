class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Mark all numbers present in nums using a Set
        Set<Integer> present = new HashSet<>();
        for (int num : nums) {
            present.add(num);
        }
        
        int current = lower;
        while (current <= upper) {
            // Find the start of a missing range
            if (!present.contains(current)) {
                int start = current;
                // Keep moving forward to find the end of the missing range
                while (current <= upper && !present.contains(current)) {
                    current++;
                }
                int end = current - 1;
                result.add(Arrays.asList(start, end));
            } else {
                current++;
            }
        }
        
        return result;

    }
}