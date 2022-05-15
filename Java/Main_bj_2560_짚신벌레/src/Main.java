import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2560.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken()); // 성체가 되는 시간
			int b = Integer.parseInt(st.nextToken()); // 개체를 만들지 않는 시간
			int d = Integer.parseInt(st.nextToken()); // 수명
			int N = Integer.parseInt(st.nextToken()); // 최종 일자
			int[] mat = new int[d];
			int res = 1;
			mat[0] = 1;
			// System.out.println(Arrays.toString(mat));
			int head = 0;
			int tail = 0;
			/*
			 * 큐로 값을 넣은 뒤, 일정 개수 이상부터는 빼기 시작한다?
			 */
			// 현재 오류 존재 (수정필요)
			for (int i = 0; i < N; i++) {
				head++;
				head%=N;
				if (i >= d)
					mat[tail++] = 0; // 짚신벌레 사망
				if (i >= a) {
					for (int j = a; j < b; j++) {
						mat[head]+=mat[((head-j)+N)%N];
					}
				}
			}
			System.out.println(res);
		}
	}

}
