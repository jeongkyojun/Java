import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10844.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		long[][] mat = new long[101][10];
		int T = Integer.parseInt(bf.readLine());
		for (int i = 1; i < 10; i++) {
			mat[1][i] = 1;
		}
		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < 10; j++) {
				if (j > 0)// 1->0 2->1 ... 9->8까지
				{
					mat[i + 1][j - 1] += mat[i][j];
					mat[i + 1][j - 1] = mat[i + 1][j - 1] % 1000000000;
				}
				if (j < 9) {
					mat[i + 1][j + 1] += mat[i][j];
					mat[i + 1][j + 1] = mat[i + 1][j + 1] % 1000000000;
				}
			}
		}

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(bf.readLine());
			for (int i = 1; i <= n; i++)
				System.out.println(Arrays.toString(mat[i]));
			long sum = 0;
			for (int i = 0; i < 10; i++) {
				sum += mat[n][i];
				sum = sum % 1000000000;
			}
			System.out.println(sum);
		}
	}
}
