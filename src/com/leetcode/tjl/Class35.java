public class Class35 {
    public static void main(String[] args) {
        int [] nums={1,3,5,6};
        int target=7;
        System.out.println(searchInsert(nums,target));
    }
    public static int searchInsert(int[] nums, int target) {
        int fromIndex = 0;
        int toIndex = nums.length - 1;
        return halfSearch(nums, target,  fromIndex, toIndex);
    }

    private static int halfSearch(int[] nums, int target, int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            if (nums[fromIndex] == target) {
                return fromIndex;
            }
            if (nums[fromIndex] > target) {
                if (fromIndex == 0) {
                    return 0;
                }
                return fromIndex - 1;
            }
            return fromIndex+1;
        }
        if (toIndex==fromIndex+1){
            if (nums[fromIndex] == target) {
                return fromIndex;
            }
            if (nums[toIndex] == target) {
                return toIndex;
            }
            if (nums[fromIndex]>target){
                if (fromIndex == 0) {
                    return 0;
                }
                return fromIndex - 1;
            }
            if (nums[toIndex]<target){
                return toIndex+1;
            }
            return toIndex;
        }
        int halfIndex=(fromIndex+toIndex)/2;
        if (nums[halfIndex]==target){
            return halfIndex;
        }
        if (nums[halfIndex]>target){
            return halfSearch(nums,target,fromIndex,halfIndex);
        }
        return halfSearch(nums,target,halfIndex,toIndex);
    }

}
