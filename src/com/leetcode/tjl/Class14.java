import java.util.Arrays;

public class Class14 {
    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strings));

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length==0){
            return "";
        }
        boolean flag = true;
        int minLen=strs[0].length();
        for(String s:strs){
            if (minLen>s.length()){
                minLen=s.length();
            }
        }
        for (int i = 0; i < minLen; i++) {
            char c=strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i)!=c){
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                if (i == 0) {
                    return "";
                }
                return strs[0].substring(0, i );
            }
        }
//其实不会走到这里来的
        System.out.println("其实不会走到这里来的");
        return strs[0];
    }
}
