package m277_FindtheCelebrity;

public class FindtheCelebrity {

    // n表示标签为[0,...,n-1]
    public int findCelebrity(int n) {
        if (n <= 1) return -1;
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (knows(res, i)) res = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != res && (!knows(i, res) || knows(res, i))) return -1;
        }
        return res;
    }

    private boolean knows(int a, int b) {
        // todo
        return true;
    }
}
