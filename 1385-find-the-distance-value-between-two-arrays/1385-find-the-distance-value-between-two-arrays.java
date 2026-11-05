import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable binary search
        Arrays.sort(arr2);
        int distanceCount = 0;
        
        for (int x : arr1) {
            if (isValid(arr2, x, d)) {
                distanceCount++;
            }
        }
        
        return distanceCount;
    }
    
    // Helper function to check if all elements in arr2 are at a distance > d from x
    private boolean isValid(int[] arr2, int target, int d) {
        int left = 0;
        int right = arr2.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // If an element within the range [target - d, target + d] is found, 
            // the condition fails.
            if (Math.abs(arr2[mid] - target) <= d) {
                return false;
            }
            
            if (arr2[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return true;
    }
}