public class Class33 {
    public static void main(String[] args) {
        int[] nums = {5, 1, 3};
        int target = 5;
        System.out.println(search(nums, target));

    }

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        return halfSearch(nums, target, 0, nums.length - 1);

    }

    public static int halfSearch(int[] nums, int target, int fromIndex, int toIndx) {
        if (toIndx == fromIndex + 1) {
            if (nums[fromIndex] == target) {
                return fromIndex;
            }
            if (nums[toIndx] == target) {
                return toIndx;
            }
            return -1;
        }
        int halfIndex = (fromIndex + toIndx) / 2;
        if (nums[halfIndex] == target) {
            return halfIndex;
        }
        if (fromIndex == toIndx) {
            return -1;
        }
        int tmpR = -1;
        tmpR = halfSearch(nums, target, fromIndex, halfIndex);
        if (tmpR == -1) {
            tmpR = halfSearch(nums, target, halfIndex, toIndx);
        }
        return tmpR;

    }


}
