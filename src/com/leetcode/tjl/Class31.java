import java.util.ArrayList;
import java.util.Arrays;

public class Class31 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(Arrays.stream(nums).toArray()));
    }

    /*
    实现了，但是太慢
     */
    public static void nextPermutation(int[] nums) {
        boolean flag = false;
        for (int i = nums.length - 1; i > 0; i--) {
            ArrayList<Integer> list = minNum(Arrays.copyOfRange(nums, i, nums.length), nums[i - 1]);
            if (list.size() == 0) {
                continue;
            }
            int tmpMin = list.get(1);
            if (nums[i - 1] < tmpMin) {
                int tmpIndex = list.get(0) + i;
                nums[tmpIndex] = nums[i - 1];
                nums[i - 1] = tmpMin;
                ascOrder(nums, i);
                flag = true;
                break;
            }
        }
        if (!flag) {
            ascOrder(nums, 0);
        }
    }

    public static void ascOrder(int[] nums, int fromIndex) {
        for (int i = fromIndex; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    public static ArrayList<Integer> minNum(int[] nums, int num) {
        int tmpMin = nums[0];
        int tmpIndex = -1;
        for (int j = 0; j < nums.length; j++) {
            if (tmpMin >= nums[j] && nums[j] > num) {
                tmpMin = nums[j];
                tmpIndex = j;
            }
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (tmpIndex > -1) {
            list.add(tmpIndex);
            list.add(tmpMin);
        }
        return list;
    }


}
