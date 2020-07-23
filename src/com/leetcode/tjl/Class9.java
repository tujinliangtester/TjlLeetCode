public class Class9 {

    public boolean isPalindrome(int x) {
        String s1=String.valueOf(x);
        StringBuilder sb=new StringBuilder(s1);
        return s1.equals(sb.reverse().toString());
    }
}
