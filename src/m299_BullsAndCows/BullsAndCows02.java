package m299_BullsAndCows;

public class BullsAndCows02 {

    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() == 0) return "0A0B";
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];  // 桶排序思想，count[i]表示i出现的个数
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) bulls++;
            else {
                // count[i]>0表示secret中有count[i]个i
                // count[i]<0表示guess中有count[i]的绝对值个i
                if (count[s]++ < 0) cows++; // count[s]<0表示guess有s，所以cows++.
                if (count[g]-- > 0) cows++;// count[g]>0表示secret有g，所以cows++.
            }
        }
        return bulls + "A" + cows + "B";
    }
}
