import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5557.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			int N = Integer.parseInt(bf.readLine());
			int[] nums = new int[N];
			int[] cnt = new int[N];
			int[][] lst = new int[N][21];
			long[][] DP = new long[N][21]; // i는 nums의 idx, j는 총 결과값
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			lst[0][cnt[0]++] = nums[0];
			DP[0][nums[0]] = 1;
			//System.out.println(Arrays.toString(DP[0]));
			for (int i = 0; i < N - 2; i++) {
				// System.out.println(Arrays.toString(DP[i]));
				for (int j = 0; j < cnt[i]; j++) {
					//System.out.print(lst[i][j] + " ");
					// 현재값에 앞의 값(i+1)을 더하거나 뺀다.
					if (lst[i][j] - nums[i + 1] >= 0) {
						if (DP[i + 1][lst[i][j] - nums[i + 1]] == 0)
							lst[i + 1][cnt[i + 1]++] = lst[i][j] - nums[i + 1];
						DP[i + 1][lst[i][j] - nums[i + 1]] += DP[i][lst[i][j]];
					}
					if (lst[i][j] + nums[i + 1] <= 20) {
						if (DP[i + 1][lst[i][j] + nums[i + 1]] == 0)
							lst[i + 1][cnt[i + 1]++] = lst[i][j] + nums[i + 1];
						DP[i + 1][lst[i][j] + nums[i + 1]] += DP[i][lst[i][j]];
					}
				}
				//System.out.println();
				//System.out.println(Arrays.toString(DP[i + 1]));
			}
			System.out.println(DP[N - 2][nums[N - 1]]);
		}
	}

}
