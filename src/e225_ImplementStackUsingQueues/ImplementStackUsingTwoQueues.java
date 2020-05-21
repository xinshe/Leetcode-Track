package e225_ImplementStackUsingQueues;

import java.util.LinkedList;
import java.util.Queue;

class MyStack2 {

    private Queue<Integer> in = new LinkedList<>();
    private Queue<Integer> out = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyStack2() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        in.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int cnt = in.size();
        while (cnt-- > 1) {
            out.add(in.remove());
        }
        int top = in.remove();
        Queue<Integer> tmp = in;
        in = out;
        tmp = in;
        return top;
    }

    /** Get the top element. */
    public int top() {
        int cnt = in.size();
        while (cnt-- > 1) {
            out.add(in.remove());
        }
        int top = in.remove();
        out.add(top);
        Queue<Integer> tmp = in;
        in = out;
        tmp = in;
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return in.isEmpty();
    }
}

public class ImplementStackUsingTwoQueues {
}
