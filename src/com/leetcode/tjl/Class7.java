public class Class7 {

    public int reverse(int x) {
        String string = String.valueOf(x);
        char c = string.charAt(0);
        if (c == '-' || c == '+') {
            string = string.substring(1);
            if (c == '-') {
                return -myReverse(string);
            }
            return myReverse(string);
        } else {
            return myReverse(string);
        }
    }

    private int myReverse(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i));
        }
        long myLong = Long.parseLong(sb.toString());
        if (myLong > Integer.MAX_VALUE) {
            return 0;
        }
        return Math.toIntExact(myLong);
    }
}
