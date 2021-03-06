package m260_SingleNumberIII;

/**
 * 260. 只出现一次的数字 III
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 *
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 */
public class SingleNumberIII {

    // 第一步：所有数异或，所有数异或之后的值就是两个只出现一次的数a,b异或后的值mask。
    // 第二步：那我们用 mask & (-mask) 可以得出 mask 最低位(也就是最右边)为1的bit位对应的数diff，
    //         这里有一个很关键点就是：这个为1的bit位肯定只来之a.b其中的一个数字中的对应二进制位的1，比如我们把数字转成二进制运算看下：
    //         a：1 -> 0001
    //         b：2 -> 0010
    //         a ^ b = 0011. 不全为1的bit为进行异或操作就为1，这是异或的基本流程，然后我们操作mask & (-mask)之后得到的是0001，可以看到这个1是来之数字a的
    // 第三步：我们得到 mask & (-mask) 之后在对所有数进行&操作的话，就意味着可以将a和b区分在0和1的两个组，至于其他的数字如果相同的数字自然会分到一个组，可以用纸笔写下整个过程
    // 第四步：经过第三步之后就变成了只有一个数字存在一个其他都存在两个的数组(也就变成题目：136. 只出现一次的数字)，
    //         然后我们分别对两个组的所有数字再进行异或之后不就得到了要求的那两个数了嘛。
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        int mask = 0;
        // 1. 得到不重复的这两个数（记为 a，b）的异或结果
        for (int num : nums) {
            mask = mask ^ num;
        }
        // 2. 得到a和b最低的不同位
        int diff = mask & (-mask);
        // 3. 根据A和B最低的不同位可以将数组中的数分为两类
        for (int num : nums) {
            if ((diff & num) == 0) {
                res[0] = res[0] ^ num;
            } else {
                res[1] = res[1] ^ num;
            }
        }
        return res;
    }
}
