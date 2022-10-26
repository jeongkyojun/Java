package main.bj_1339;

// 단어수학
import java.util.*;
import java.io.*;

public class Main {

	static int max;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1339.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");

			int n = Integer.parseInt(bf.readLine());
			max = -1;
			int[][] mat = new int[n][9]; // 각 수의 자리수를 확인하기 위해 마지막에 -1을 넣는다 -> 9칸의 배열로 선언
			boolean[] B = new boolean[27]; // 현재의 알파벳이 이미 나온적 있는지를 확인
			int[] list = new int[11]; // 나온 알파벳들의 목록
			int[] num = new int[27]; // 숫자 매핑용 배열
			int cnt = 0;
			// 알파벳이 무작위로 나올 경우를 대비해 list에 나온 알파벳들을 숫자로 치환하여 채운다
			// Ex) A -> 0 , B -> 1 .... Z -> 26
			for (int i = 0; i < n; i++) {
				String str = bf.readLine(); // GCF
				for (int j = 0; j < str.length(); j++) {
					int alph = str.charAt(j) - 65; // A = 0 , B = 1 , ....
					if (!B[alph]) {
						B[alph] = true;
						list[cnt++] = alph;
					}
					mat[i][j] = alph;
					mat[i][j + 1] = -1;
				}
			}
			System.out.println("mat");
			for(int i=0;i<n;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}
			
			System.out.println("num");
			System.out.println(Arrays.toString(num));
			for (int x = 9; x > 9 - cnt; x--) { // 가장 큰 값이므로 9부터 하나씩 줄이며 채운다.
				int idx = 0;
				for (int i = 0; i < cnt; i++) { // 가장 큰 결과를 가져다주는 칸으로 넣는다.
					if (num[list[i]] == 0) {
						num[list[i]] = x;
						int res = cal(mat, num); // 계산
						if (res > max) { // 계산한 값이 최대일  경우
							max = res; // 현재까지의 최댓값과 인덱스값을 갱신
							idx = i;
						}
						num[list[i]] = 0; // 다시 0으로 초기화
					}
				}
				num[list[idx]] = x; // 저장해놓은 인덱스값에 해당값 매핑
			}
			System.out.println("after num");
			System.out.println(Arrays.toString(num));
			//max = cal(mat, num); // 최대값을 매핑한 것을 저장
			System.out.println(max);
		}
	}

	// 알파벳이 매핑되어있는 mat,
	// 알파벳의 숫자가 저장되어있는 num (int[27])
	static int cal(int[][] mat, int[] num) {
		int sum = 0;
		for (int i = 0; i < mat.length; i++) {
			int r = 0;
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == -1)
					break;
				r *= 10;
				r += num[mat[i][j]];
			}
			sum += r;
		}
		return sum;
	}

}
