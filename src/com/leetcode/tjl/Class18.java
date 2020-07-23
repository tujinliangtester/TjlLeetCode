import java.util.ArrayList;
import java.util.List;

public class Class18 {
    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6};
        int target=0;
        fourSum(nums,target);
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> listList=new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                for (int k=j+1;k<nums.length;k++){
                    for (int n=k+1;n<nums.length;n++){
                        System.out.print(i);
                        System.out.print(j);
                        System.out.print(k);
                        System.out.println(n);
                        if (nums[i]+nums[j]+nums[k]+nums[n]==target){
                            List<Integer> integerList=new ArrayList<>();
                            integerList.add(nums[i]);
                            integerList.add(nums[j]);
                            integerList.add(nums[k]);
                            integerList.add(nums[n]);
                            integerList.sort(Integer::compareTo);
                            if (!listList.contains(integerList)){
                                listList.add(integerList);
                            }
                        }
                    }
                }
            }
        }
        return listList;
    }
}
