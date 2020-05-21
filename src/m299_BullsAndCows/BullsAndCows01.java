package m299_BullsAndCows;

public class BullsAndCows01 {

    /**
     * time complexity：O(n)
     * space complexity：O(1)
     */
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() == 0) return "0A0B";
        int len = secret.length();
        int[] count = new int[10];  // 桶排序思想，count[i]表示i出现的个数
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < len; i++) { // 先遍历secret，求出bull数目
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else count[secret.charAt(i) - '0']++;
        }
        for (int i = 0; i < len; i++) { // 在遍历guess，求出cow数目
            if (secret.charAt(i) != guess.charAt(i) && count[guess.charAt(i) - '0'] > 0) { // i的个数大于0就表示i在secret中出现过
                count[guess.charAt(i) - '0']--; // secret中出现的数每个只能算一次，所以这里要减1
                cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
