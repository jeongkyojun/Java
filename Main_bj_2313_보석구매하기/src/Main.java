import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2313.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bf.readLine());
		long res = 0; // 정답이므로 long
		int[][] list = new int[n][2]; // 지점
		for (int i = 0; i < n; i++) {
			int t = Integer.parseInt(bf.readLine()); // 범위
			int[] mat = new int[t + 1]; // 누적합
			StringTokenizer st = new StringTokenizer(bf.readLine());
			// 현재의 누적값을 x라고 하면
			// 현재 누적값에서 최댓값 = 현재 누적값 - 지금까지 누적값중 최소가 되는 값
			int min = 0, idx = 0; // 아무것도 없는 경우 = 0
			long max = Long.MIN_VALUE; // 최댓값을 초기화
			for (int j = 1; j <= t; j++) {
				mat[j] = mat[j - 1] + Integer.parseInt(st.nextToken());
				// 1. 현재누적값 - 최소누적값 > 최댓값 (맨 처음은 여기서 초기화된다)
				// 2. 현재누적값 - 최소누적값 = 최댓값 이면서 연속으로 사는 보석의 간격이 현재 개수보다 작은경우
				// System.out.println(j+" : "+(mat[j]-mat[idx]));
				// 1 j  -  1 idx = idx+1 j 가 된다. => (최대) - (최소) 가 되도록 한다.
				// 만약 여러개면 간격이 가장 짧은 것을 한다 = j-idx를 수행
				if ((mat[j] - mat[idx] > max) || 
						(mat[j] - mat[idx] == max && j - idx < list[i][1] - list[i][0])) {
					max = mat[j] - mat[idx]; // 최댓값 갱신
					list[i][0] = idx;
					list[i][1] = j;
				}
				if (mat[j] <= min) {
					idx = j; // 현재 인덱스를 포함, 가장 작은 인덱스를 비교, 적용한다.
					// 같은 인덱스여도 가장 j와 가까운 인덱스로 이동한다.
					min = mat[j];
				}
			}
			System.out.println(max);
			// System.out.println(Arrays.toString(mat));
			res += max;

		}
		System.out.println(res);
		for (int i = 0; i < n; i++) {
			System.out.print(list[i][0] + 1 + " ");
			System.out.println((list[i][1]));
		}
	}

}
