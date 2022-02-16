import java.util.*;
import java.io.*;

// 2263_트리의 순회
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_2263.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");

			int N = Integer.parseInt(bf.readLine());
			int l = 1;
			int depth = 0;
			while (N > l) {
				l = l << 1;
				depth++;
			}
			l--;

			System.out.println("dep : " + depth);

			int[] in_order = new int[N];
			int[] post_order = new int[N];

			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < N; i++) {
				in_order[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < N; i++) {
				post_order[i] = Integer.parseInt(st.nextToken());
			}
			int[] res_order = new int[l];
			ㄱㄷ
			System.out.println("res : "+Arrays.toString(res_order));
			//PrintTree(in_order, 0, l, depth);
			//System.out.println();
		}
	}

	// 트리 출력까지는 성공
	static void PrintTree(int[] mat, int s, int e, int depth) {
		if (depth == 1) {
			if (mat[s] != 0)
				System.out.print(mat[s] + " ");
		} else {
			int mid = (s + e) / 2;
			if (mat[s] != 0)
				System.out.print(mat[mid] + " ");
			PrintTree(mat, s, mid - 1, depth - 1);
			PrintTree(mat, mid + 1, e, depth - 1);
		}
	}

	// 1. 정답
	// 2. post_order
	// 3. in_order
	// 4. 정답 사이즈 l
	// 5. 
	static void returnTree(int[]res_order,int[] post_order, int[] in_order, int res_size) {
		int i = post_order.length -1;
		int j = res_size/2;// 15/2 = 7
		res_order[j] = post_order[i];
	}
}
