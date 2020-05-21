package m134_GasStation;

public class MyGasStation {

    /**
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        // 题目说了输入的两个数组都不为空，且长度相等，所以这里不做特殊情况判断
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            int remain = 0;
            int cur = i;
            while (remain + gas[cur] - cost[cur] >= 0) {
                remain = remain + gas[cur] - cost[cur];
                cur = (cur + 1) % len;
                if (cur == i) return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
