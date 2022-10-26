import java.io.*;
import java.util.*;

public class Solution1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_01.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			System.out.print("#" + tc + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(bf.readLine());
			int[] baby = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			st = new StringTokenizer(bf.readLine());
			int[] mom = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
			}
		}

	}

}
