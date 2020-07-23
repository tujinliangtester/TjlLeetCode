import java.util.Arrays;

public class Class167 {
    public static void main(String[] args) {
//        [12,13,23,28,43,44,59,60,61,68,70,86,88,92,124,125,136,168,173,173,180,199,212,221,227,230,277,282,306,314,316,321,325,328,336,337,363,365,368,370,370,371,375,384,387,394,400,404,414,422,422,427,430,435,457,493,506,527,531,538,541,546,568,583,585,587,650,652,677,691,730,737,740,751,755,764,778,783,785,789,794,803,809,815,847,858,863,863,874,887,896,916,920,926,927,930,933,957,981,997]
//        542
        int[] numbers=new int[]{12,13,23,28,43,44,59,60,61,68,70,86,88,92,124,125,136,168,173,173,180,199,212,221,227,230,277,282,306,314,316,321,325,328,336,337,363,365,368,370,370,371,375,384,387,394,400,404,414,422,422,427,430,435,457,493,506,527,531,538,541,546,568,583,585,587,650,652,677,691,730,737,740,751,755,764,778,783,785,789,794,803,809,815,847,858,863,863,874,887,896,916,920,926,927,930,933,957,981,997};
        int target=542;
        Arrays.stream(twoSum(numbers, target)).forEach(
                System.out::println
        );
        System.out.println(twoSum(numbers,target).toString());
    }
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i <numbers.length ; i++) {
            int tmp=target-numbers[i];
            int j=findTmp(numbers,i+1,numbers.length-1,tmp);
            if (j>-1){
                return new int[]{i,j};
            }
        }
        return new int[2];
    }

    private static int findTmp(int[] numbers, int fromI, int toI, int tmp) {
        if (toI<=fromI){
            if (numbers[toI]==tmp){
                return toI;
            }
            return -1;
        }
        if (toI==fromI+1){
            if (numbers[toI]==tmp){
                return toI;
            }
            if (numbers[fromI]==tmp){
                return fromI;
            }
            return -1;
        }
        int k=(fromI+toI)/2;
        if (numbers[k]==tmp){
            return k;
        }else if(numbers[k]>tmp){
            return findTmp(numbers,fromI,k-1,tmp);
        }else {
            return findTmp(numbers,k+1,toI,tmp);
        }
    }
}
