package e7_ReverseInteger;

/**
 * ÌâÄ¿ÃèÊö£º
 * https://leetcode.com/problems/reverse-integer/description/
 */

public class ReverseInteger {
	public static void main(String[] args) {
		System.out.println(reverse01(1463847412));
	}
	
	
	public static int reverse(int x) {
        int pop = 0;
        Long res = 0L;
        while(x != 0) {
        	pop = x % 10;
        	x /= 10;
        	
        	res = res * 10 + pop;
        	if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
        		return 0;
        }
        
		return res.intValue();
    }
	
	public static int reverse01(int x) {
        int pop = 0;
        int res = 0;
        while(x != 0) {
        	pop = x % 10;
        	x /= 10;
        	
//        	Integer.MAX_VALUE=2147483647		
//        	Integer.MIN_VALUE=-2147483648 
        	if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
        	res = res * 10 + pop;
        	
        }
        
		return res;
    }
}
