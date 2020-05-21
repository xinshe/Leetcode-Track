package m6_ZigZagConversion;

public class ZigZagConversion {

	public static void main(String[] args) {
		System.out.println(convert("ABCDE", 2));
		//		System.out.println(convert("PAYPALISHIRING", 4));
	}

	/**
	 * �����Լ��Ľⷨ�����õ�����������ͼ�� ӳ�䵽��ά�����еĹ��ɡ����Ҳ����һ����ά���鱣�档
	 * ���ȣ�����һ����ά���飬����*�ַ������ȣ��˷ѿռ䣩���������ʼ��Ϊȫ��Ϊ0,��������ֱ������Ӧ����ȫ��Ϊ' ',���Ǻ����ж���ʱ���ж� ����������֪Ϊ�Σ�
	 * Ȼ�󣬱����ַ�������ÿ���ַ���ӵ��ַ���ά�����У��ٶ��ַ���һ��Ӧ���������е�λ�ý��м����жϣ������ǰ�ַ��������һ�л����м��У�
	 * ��row--��col++���������������λ�ã���ֱ��row++�Ϳ����ˡ�
	 * ��󣬶Զ�ά��������жϣ�������ַ����������
	 * ʱ�临�Ӷȣ�O(n)��� ����»���O(n^2)������numRows==s.lengthʱ
	 * �ռ临�Ӷȣ�O(numRows*n)
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
