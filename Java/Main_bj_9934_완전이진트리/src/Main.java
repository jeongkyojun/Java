
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_9934.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");

			int depth = Integer.parseInt(bf.readLine());
			int N = (1 << depth) - 1;
			int[] mat = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < N; i++) {
				mat[i] = Integer.parseInt(st.nextToken());
			}

			PrintTree(mat, 0, mat.length - 1, depth);
		}
	}

	static void PrintTree(int[] mat, int low, int high, int depth) {
		Queue<int[]> Q = new LinkedList<int[]>();

		Q.offer(mat);
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j < 1 << i; j++) {
				int[] v = Q.poll();
				int mid = (v.length) / 2;
				System.out.print(v[mid]+" ");
				//if (depth > 1) {
					Q.offer(Arrays.copyOfRange(v, 0, mid));
					Q.offer(Arrays.copyOfRange(v, mid + 1, v.length));
				
			}
			System.out.println();
		}
		System.out.println();
	}

}
