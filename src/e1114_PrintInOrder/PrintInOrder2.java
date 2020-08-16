package e1114_PrintInOrder;

import java.util.concurrent.atomic.AtomicInteger;


public class PrintInOrder2 {

    // 参考：https://leetcode-cn.com/problems/print-in-order/solution/an-xu-da-yin-by-leetcode/

    private AtomicInteger done = new AtomicInteger(0);

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        done.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (done.get() != 1) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        done.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (done.get() != 2) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
