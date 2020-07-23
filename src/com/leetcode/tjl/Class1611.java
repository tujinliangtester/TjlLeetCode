import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Class1611 {
    public static void main(String[] args) {
        String s1 = readFileContent("C:/tjl/数据结构和算法/java/tmp/1");
        String s2 = readFileContent("C:/tjl/数据结构和算法/java/tmp/2");
        System.out.println(s1);
        System.out.println(s2);

        Set set1 = Arrays.stream(s1.substring(1, s1.length() - 1).split(",")).collect(Collectors.toSet());
        Set set2 = Arrays.stream(s2.substring(1, s2.length() - 1).split(",")).collect(Collectors.toSet());
        Set set3 = new HashSet();
        Set set4 = new HashSet();
        set3.clear();
        set3.addAll(set1);
        set3.removeAll(set2);
        System.out.println(set3);
        set4.clear();
        set4.addAll(set2);
        set4.removeAll(set1);
        System.out.println(set4);
    }

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] tmp = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            tmp[i] = longer * i + shorter * (k - i);
        }
        return tmp;
    }

}
