package m134_GasStation;

public class GasStation {

    /**
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int remain = 0;  // 汽油（累计）剩余量
        int lack = 0;  // 汽油（累计）缺少量
        int start = 0; // 记录当前这一次开始的位置
        for (int i = 0; i < gas.length; i++) {
            remain = remain + gas[i] - cost[i];
            if (remain < 0) {
                lack += remain;
                remain = 0;
                start = i + 1;
            }
        }
        return remain + lack >= 0 ? start : -1;
    }
}
