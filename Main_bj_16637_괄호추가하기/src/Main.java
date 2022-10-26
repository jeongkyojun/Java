// 괄호 추가하기
import java.io.*;
import java.util.*;

public class Main {

	static int max;

	static int Max(int a, int b) {
		if (a > b)
			return a;
		return b;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_16637.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			max = 0;
			// 줄의 길이
			int n = Integer.parseInt(bf.readLine());
			String s = bf.readLine();
			char[] cal = new char[n / 2 + 1]; // 계산식을 저장, 마지막은 /로 처리
			int[] num = new int[n / 2 + 1]; // 숫자를 저장
			boolean[] B = new boolean[n / 2 + 1];
			int p = 0;
			cal[p] = '/';
			for (int i = 0; i < n; i++) {
				if (i % 2 == 0) {
					num[p] = s.charAt(i) - 0x30;
					switch (cal[p]) {
					case '+':
						max += num[p];
						break;
					case '-':
						max -= num[p];
						break;
					case '*':
						max *= num[p];
						break;
					default:
						max = num[p];
						break;
					}
					p++;
				} else
					cal[p] = s.charAt(i);
			}
			//System.out.println(max);
			System.out.println(Arrays.toString(cal));
			System.out.println(Arrays.toString(num));

			subset(num, cal, B, num.length - 1, 1);
			System.out.println(max);
		}
		bf.close();
	}

	static void subset(int[] num, char[] cal, boolean[] B, int n, int cnt) {
		if (n <= cnt) {
			int res = num[0];
			for (int i = 1; i < num.length; i++) {
				if (B[i] && i<num.length-1) {
					int tmp = num[i];
					i++;
					switch (cal[i]) {
					case '+':
						tmp += num[i];
						break;
					case '-':
						tmp -= num[i];
						break;
					case '*':
						tmp *= num[i];
						break;
					default:
						break;
					}
					switch (cal[i - 1]) {
					case '+':
						res += tmp;
						break;
					case '-':
						res -= tmp;
						break;
					case '*':
						res *= tmp;
						break;
					default:
						break;
					}
				} else {
					switch (cal[i]) {
					case '+':
						res += num[i];
						break;
					case '-':
						res -= num[i];
						break;
					case '*':
						res *= num[i];
						break;
					default:
						break;
					}
				}
			}
			if (res > max) {
				System.out.print("res : " + res + " , ");
				System.out.println(Arrays.toString(B));
				max = res;
			}
			return;
		}
		for (int i = cnt; i < num.length; i++) {
			B[i] = true;
			subset(num, cal, B, n, cnt + 2);
			B[i] = false;
		}
	}
}
