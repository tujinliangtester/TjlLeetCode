import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Class34 {
    public static void main(String[] args) {
        int[] nums={1,1,2};
        int target=1;
        Arrays.stream(searchRange(nums, target))
                .forEach(
                        System.out::println
                );
    }
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int fromIndex = 0;
        int toIndex = nums.length - 1;
        List<Integer> res = new ArrayList<Integer>();
        halfSearch(nums, target, fromIndex, toIndex, res);
        if (res.size() == 0) {
            return new int[]{-1, -1};
        }
        if (res.size() == 1) {
            return new int[]{res.get(0), res.get(0)};
        }
        return new int[]{res.stream().min(Integer::compareTo).get(), res.stream().max(Integer::compareTo).get()};
    }

    private static void halfSearch(int[] nums, int target, int fromIndex, int toIndex, List<Integer> res) {
        if (toIndex==fromIndex){
            if (nums[fromIndex] == target) {
                res.add(fromIndex);
            }
            return;
        }
        if (toIndex==fromIndex+1){
            if (nums[fromIndex] == target) {
                res.add(fromIndex);
            }
            if (nums[toIndex] == target) {
                res.add(toIndex);
            }
            return;
        }
        int halfIndex = (fromIndex + toIndex) / 2;
        if (nums[halfIndex] == target) {
            res.add(halfIndex);
            halfSearch(nums,target,fromIndex,halfIndex-1,res);
            halfSearch(nums,target,halfIndex+1,toIndex,res);
        }
        else if (nums[halfIndex]>target){
            halfSearch(nums,target,fromIndex,halfIndex-1,res);
        }else {
            halfSearch(nums,target,halfIndex+1,toIndex,res);
        }
    }

}
