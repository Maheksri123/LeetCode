class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        // Pointer for placing unique elements
        int writeIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            // If we find a new unique element
            if (nums[i] != nums[writeIndex - 1]) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }

        return writeIndex;
    }
}