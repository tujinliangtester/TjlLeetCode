import java.math.BigInteger;

public class Class8 {
    public static void main(String[] args) {
        String str = "20000000000000000000";
        System.out.println(myAtoi(str));
    }

    public static int myAtoi(String str) {
        //flag 0:未开头 1：已开头，符号开头 2：已开头，数字开头 3：已结束
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (flag == 0) {
                if (c == ' ') {
                    continue;
                } else if (c == '-' || c == '+') {
                    flag = 1;
                    sb.append(c);
                    continue;
                } else if (c >= '0' && c <= '9') {
                    flag = 2;
                    sb.append(c);
                    continue;
                }
                return 0;

            } else {
                if (c == ' ') {
                    break;
                }
                if (c == '-' || c == '+') {
                    break;
                }
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                    continue;
                }
                break;
            }
        }
        if (sb.toString().length() == 0) {
            return 0;
        }
        if (flag==1 && sb.toString().length()==1){
            return 0;
        }

        BigInteger bigInteger=new BigInteger(sb.toString());

        if (bigInteger.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0){
            return Integer.MAX_VALUE;
        }else if (bigInteger.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0){
            return Integer.MIN_VALUE;
        }
        return bigInteger.intValue();
    }

}
