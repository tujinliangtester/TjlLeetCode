import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;

public class Class10 {
    public static void main(String[] args) {
        String s = "aaa";
        String p = "ab*a*c*a";
        System.out.println(isMatch3(s, p));
    }

    public static boolean isMatch(String s, String p) {
        while (p.contains("**")) {
            p = p.replace("**", "*");
        }

        int i = 0;
        int j = 0;
        for (; i < s.length() && j < p.length(); ) {
            if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if (p.charAt(j) == '*') {
                //匹配1次或多次 todo
                if (j > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i))) {
                    j--;
                }
                //匹配0次
                else {
                    j++;
                }
            }
            //匹配0次
            else if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                j += 2;
            } else {
                return false;
            }
        }
        if (i < s.length()) {
            return false;
        }
        for (; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                continue;
            } else if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    //换一种思路，将‘*’全部处理掉
    public static boolean isMatch2(String s, String p) {
        if (s == null || s.length() == 0) {
            return p == null || p.length() == 0;
        }
        StringBuilder sb = new StringBuilder(p);
        ArrayList<Integer> arrayList = null;
        while (p.contains("*")) {
            sb = new StringBuilder();
            arrayList = new ArrayList();
            for (int i = 1; i < p.length(); i++) {
                if (p.charAt(i) == '*') {
                    arrayList.add(i - 1);
                } else {
                    sb.append(p.charAt(i));
                }
            }
        }
        p = sb.toString();
        //确定匹配次数
        int maxNum = s.length() - p.length();
        if (arrayList != null) {
            maxNum += arrayList.size();
        } else {
            return s.equals(p);
        }
        if (maxNum < 0) {
            return false;
        }
        return myCheck(s, p, arrayList, maxNum);
    }

    private static boolean myCheck(String s, String p, ArrayList<Integer> arrayList, int maxNum) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        arrayList.forEach(
                n -> {
                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int i = 0; i < maxNum + 1; i++) {
                        StringBuilder sb = new StringBuilder();
                        for (int k = 0; k < i; k++) {
                            sb.append(p.charAt(n));
                        }
                        stringArrayList.add(sb.toString());
                    }
                    arrayLists.add(stringArrayList);
                }
        );
        //todo 好像还是很复杂，且方向不对
        for (int i = 0; i < arrayLists.size(); i++) {

        }


        return true;
    }

    //再换种思路，匹配的不好找，那不匹配的是不是就好找了？
    public static boolean isMatch3(String s, String p) {
        //处理 **
        while (p.contains("**")) {
            p = p.replace("**", "*");
        }
        //处理 a*a->a*   a*c*a->a*c*
        // todo 其实发现不得行，因为还有.*a 这种 又走入误区了
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < p.length(); k++) {
            if (k + 2 < p.length() && p.charAt(k + 1) == '*') {
                if (p.charAt(k) == p.charAt(k + 2)) {
                    sb.append(p.charAt(k));
                    sb.append(p.charAt(k + 1));
                    k += 2;
                } else {
                    sb.append(p.charAt(k));
                }
            } else {
                sb.append(p.charAt(k));
            }
        }
        p = sb.toString();
        int i = 0;
        int j = 0;
        for (; i < s.length() && j < p.length(); ) {
            if (j + 1 < p.length()) {
                if (p.charAt(j + 1) == '*') {
                    if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                        i++;
                    } else {
                        j += 2;
                    }
                } else {
                    if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                        i++;
                        j++;
                    } else {
                        return false;
                    }
                }
            } else {
                if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }
        if (i < s.length()) {
            return false;
        }
        //处理剩余p
        int n = j;
        StringBuilder sb2 = new StringBuilder();
        for (; n < p.length(); n++) {
            if (n < p.length() - 1 && p.charAt(n + 1) == '*') {
                n++;
            } else {
//                sb2.append(p.charAt(n));
                return false;
            }
        }
        return true;
    }

}
