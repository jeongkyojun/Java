import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= t; tc++) {
			System.out.println("#" + tc + " ");

			int n = Integer.parseInt(bf.readLine());
			int[][] mat = new int[n][3];
			int min = 1000001;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 3; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
				System.out.println(Arrays.toString(mat[i]));
			}
		}

	}
}
