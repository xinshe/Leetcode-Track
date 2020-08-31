import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ShareResource {
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    char flag = 'a';

    public void printA() throws InterruptedException {
        lock.lock();

        while (flag != 'a') {
            c1.await();
        }

        System.out.println(flag);
        flag = 'b';
        c2.signal();

        lock.unlock();
    }

    public void printB() throws InterruptedException {
        lock.lock();

        while (flag != 'b') {
            c2.await();
        }

        System.out.println(flag);
        flag = 'c';
        c3.signal();

        lock.unlock();
    }

    public void printC() throws InterruptedException {
        lock.lock();

        while (flag != 'c') {
            c3.await();
        }

        System.out.println(flag);
        flag = 'a';
        c1.signal();

        lock.unlock();
    }

}

public class Main {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            while (true) {
                try {
                    shareResource.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    shareResource.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    shareResource.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}