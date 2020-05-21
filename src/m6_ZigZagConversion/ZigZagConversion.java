package m6_ZigZagConversion;

public class ZigZagConversion {

	public static void main(String[] args) {
		System.out.println(convert("ABCDE", 2));
		//		System.out.println(convert("PAYPALISHIRING", 4));
	}

	/**
	 * 这是自己的解法，利用的是这个锯齿形图案 映射到二维数组中的规律。结果也是用一个二维数组保存。
	 * 首先，创建一个二维数组，行数*字符串长度（浪费空间），并将其初始化为全部为0,（讲道理直接声明应该是全部为' ',但是后面判读的时候判断 不出来，不知为何）
	 * 然后，遍历字符串，将每个字符添加到字符二维数组中，再对字符下一个应加入数组中的位置进行计算判断，如果当前字符是在最后一行或者中间列，
	 * 就row--，col++；如果是在其他的位置，就直接row++就可以了。
	 * 最后，对二维数组进行判断，构造出字符串并输出。
	 * 时间复杂度：O(n)。最坏 情况下会有O(n^2)，即当numRows==s.length时
	 * 空间复杂度：O(numRows*n)
	 */
	public static String convert(String s, int numRows) {
		if(numRows == 1) return s;

		char[] ch = s.toCharArray();
		int numCols = s.length();
		char[][] resArr = new char[numRows][numCols];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				resArr[i][j] = '0';
			}
		}

		int row = 0;
		int col = 0;
		for(int i = 0; i < ch.length; i++) {
			resArr[row][col] = ch[i];
			if(row == numRows-1 || col%(numRows-1) != 0) {
				row--;
				col++;
			} else {
				row++;
			}
		}

		StringBuffer stb = new StringBuffer();
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				if(resArr[i][j] != '0')
					stb.append(resArr[i][j]);
			}
		}

		return stb.toString();
	}

	
}
