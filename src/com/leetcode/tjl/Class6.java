import com.sun.deploy.util.StringUtils;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Class6 {
    public static void main(String[] args) {
        String s = "A";
        int numRows = 1;
        System.out.println(convert(s, numRows));
    }


    public static String convert(String s, int numRows) {
        //1.多层for循环嵌套
        //2.Python等多维数组
        //3.不用二维数组了，直接找规律：
        // 行数n，一下一上对应的字符数量2*n-2 按照行逐一进行读取拼接即可
        if (numRows==1){
            return s;
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length() + 2 * numRows - 2; j += 2 * numRows - 2) {
                if (j < s.length()) {
                    sb.append(s.charAt(j));
//                    System.out.print(s.charAt(j));
                }
                int k = j + 2 * numRows - 2 - 2 * (i);
                if (i > 0 && k > j && k < s.length()) {
                    sb.append(s.charAt(k));
//                    System.out.print(s.charAt(k));
                }
            }
//            System.out.println();

        }

//        System.out.println(arrayList.size());
        return sb.toString();

    }
}
