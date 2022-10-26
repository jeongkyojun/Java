
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_10163.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			System.out.println("####"+test_case);
			
			int[][] mat = new int[1002][1002];
			int[] p = new int[101];
			int cnt = 0;
			int N = Integer.parseInt(bf.readLine());
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int[] a = new int[4];
				for (int x = 0; x < 4; x++) {
					a[x] = Integer.parseInt(st.nextToken());
				}
				for (int x = a[0]; x < a[0]+a[2]; x++) {
					for (int y = a[1]; y < a[1]+a[3]; y++) {
						mat[x][y] = i;
					}
				}
			}
			for (int i = 0; i < 1002; i++) {
				for (int j = 0; j < 1002; j++) {
					p[mat[i][j]]++;
				}
			}
			for (int i = 1; i <= N; i++)
				System.out.println(p[i]);
			
			System.out.println();
		}
	}

}
