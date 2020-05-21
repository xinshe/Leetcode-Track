package m8_StringToInteger;

public class StringToInteger {

    public static int myAtoi(String str) {
        char[] ch = str.toCharArray();
        int len = ch.length;

        boolean flag = false;
        boolean isNegative = false;
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (!flag) {
                if (ch[i] == ' ') {

                } else if (ch[i] == '-') {
                    isNegative = true;
                    flag = true;
                } else if (ch[i] == '+') {
                    flag = true;
                } else if (Character.isDigit(ch[i])) {
                    stb.append(ch[i]);
                    flag = true;
                } else {
                    break;
                }
            } else {
                if (!Character.isDigit(ch[i])) {
                    break;
                }
                stb.append(ch[i]);
            }
        }

        while (true) {
            if (stb.length() == 0)
                return 0;
            if (stb.charAt(0) != '0')
                break;
            stb.deleteCharAt(0);
        }

        if (stb.length() > 10)
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        long res = Long.parseLong(stb.toString());
        if (isNegative)
            return res*(-1) < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)res*(-1);
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi(".1"));
        System.out.println(myAtoi("20000000000000000000"));
        System.out.println(myAtoi("  0000000000012345678"));

        System.out.println(myAtoi("    0000000000000   "));
        System.out.println(myAtoi("-words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }
}
