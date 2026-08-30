class Solution {
    public int[] sortedSquares(int[] nums) {
      int pos=0;
      int[] arr= new int[nums.length];
      for(int i=0;i<nums.length; i++){
      arr[pos]=nums[i]*nums[i];
      pos++;
      }  
      Arrays.sort(arr);
      return arr;
    }
}