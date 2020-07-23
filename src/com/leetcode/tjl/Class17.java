import java.util.ArrayList;
import java.util.List;

public class Class17 {
    public static void main(String[] args) {
        String digits="23";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {
        char[] num2 = {'a', 'b', 'c'};
        char[] num3 = {'e', 'f', 'd'};
        char[] num4 = {'g', 'h', 'i'};
        char[] num5 = {'j', 'k', 'l'};
        char[] num6 = {'m', 'n', 'o'};
        char[] num7 = {'p', 'q', 'r', 's'};
        char[] num8 = {'t', 'u', 'v'};
        char[] num9 = {'w', 'x', 'y', 'z'};
        ArrayList<char[]> arrayList = new ArrayList<>();
        arrayList.add(num2);
        arrayList.add(num3);
        arrayList.add(num4);
        arrayList.add(num5);
        arrayList.add(num6);
        arrayList.add(num7);
        arrayList.add(num8);
        arrayList.add(num9);

        ArrayList<char[]> arrayList1 = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            int d = Integer.parseInt( digits.substring(i,i+1));
            arrayList1.add(arrayList.get(d - 2));
        }
        List<String> targetList=new ArrayList<>();
        ArrayList tmpList=new ArrayList();
        int n=digits.length();
        return myDecar(arrayList1,targetList,tmpList,n);
    }

    private static List<String> myDecar(List<char[]> list,List<String> targetList,ArrayList tmpList,int n) {
        int layers=list.size();
        for (int i=0;i<layers;i++){
            for (char c : list.get(i)) {
                tmpList.add(c);
                myDecar(list.subList(i+1,layers),targetList,tmpList,n);
                if (tmpList.size() == n) {
                    StringBuilder sb = new StringBuilder();
                    tmpList.forEach(
                            sb::append
                    );
                    targetList.add(sb.toString());
                }
                tmpList.remove(tmpList.size()-1);
            }
        }
        return targetList;
    }
}
