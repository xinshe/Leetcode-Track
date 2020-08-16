import java.util.*;

class ShareResource {
    String numbers;
    String strings;
    private boolean flag;
    private int index1 = 0;
    private int index2 = 0;

    public ShareResource(String numbers, String strings) {
        this.numbers = numbers;
        this.strings = strings;
    }

    public void printNumber() throws InterruptedException {
        synchronized (this) {
           while (flag) {
               this.wait();
           }
           if (index1 < numbers.length()) {
               System.out.println(numbers.charAt(index1++));
           }
           flag = !flag;
           this.notify();
        }
    }

    public void printString() throws InterruptedException {
        synchronized (this) {
            while (!flag) {
                this.wait();
            }
            if (index2 < strings.length()) {
                System.out.println(numbers.charAt(index2++));
            }
            flag = !flag;
            this.notify();
        }
    }

}

public class Main {

    static int n1 = 9;
    static int n2 = 5;

    public static void main(String[] args) {
        ShareResource sr = new ShareResource("123456789", "ABCDE");
//        n1 = sr.numbers.length();
//        n2 = sr.strings.length();

        new Thread(() -> {
            while (n1-- > 0) {
                try {
                    sr.printNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (n2-- > 0) {
                try {
                    sr.printNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}