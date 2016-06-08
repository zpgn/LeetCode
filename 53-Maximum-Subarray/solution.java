public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length==0) return 0;
        //if (nums.length==1) return nums[0]>0 ? nums[0] : 0;
        int[] res = new int[nums.length];
        int out = nums[0];
        res[0]=nums[0];
        for (int i=1; i<res.length; i++){
            if (res[i-1]<0) res[i]=nums[i];
            else res[i]=res[i-1]+nums[i];
            out=Math.max(out,res[i]);
        }
        return out;
    }
}