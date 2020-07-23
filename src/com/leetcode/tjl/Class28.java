public class Class28 {
    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issi";
        haystack.indexOf(needle);
        strStr2(haystack, needle);
    }

    public static int strStr2(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int n = 1;
        for (; n < needle.length(); n++) {
            if (needle.charAt(n) == needle.charAt(0)) {
                break;
            }
        }
        int k = 0;
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = ++k;
                j = 0;
            }
        }
        if (j == needle.length()) {
            return k;
        }
        return -1;

    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return -1;
        }
        int k = 0;
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = ++k;
                j = 0;
            }
        }
        if (j == needle.length()) {
            return k;
        }
        return -1;
    }

}
