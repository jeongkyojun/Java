package main.bj_1132;

import java.util.*;
import java.io.*;

보류코드

public class Main {

	static long max;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1132.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			/*
			 * 필요한 것
			 * 맨 앞자리에 나오는 알파벳의 집합
			 * 위 집합을 이용해 집합을 제외한 수들 중 0의 값을 부여해야 한다.
			 * 아래의 코드에서 어떤것을 추가해야 할까?
			 * 
			 */
			int n = Integer.parseInt(bf.readLine());
			max = -1;
			int[][] mat = new int[n][13]; // 각 수의 자리수를 확인하기 위해 마지막에 -1을 넣는다 -> 9칸의 배열로 선언
			boolean[] B = new boolean[27]; // 현재의 알파벳이 이미 나온적 있는지를 확인
			int[] list = new int[11]; // 나온 알파벳들의 목록
			int[] num = new int[27]; // 숫자 매핑용 배열
			int cnt = 0;
			// 알파벳이 무작위로 나올 경우를 대비해 list에 나온 알파벳들을 숫자로 치환하여 채운다
			// Ex) A -> 0 , B -> 1 .... Z -> 26
			for (int i = 0; i < n; i++) {
				String str = bf.readLine(); // 줄을 읽는다
				for (int j = 0; j < str.length(); j++) {
					int alph = str.charAt(j) - 65; // 알파벳을 숫자로 치환한값
					if (!B[alph]) { // 지
						B[alph] = true;
						list[cnt++] = alph;
					}
					mat[i][j] = alph;
					mat[i][j + 1] = -1;
				}
			}
			System.out.println(max);
		}
	}

	// 알파벳이 매핑되어있는 mat,
	// 알파벳의 숫자가 저장되어있는 num (int[27])
	static long cal(int[][] mat, int[] num) {
		long sum = 0L;
		for (int i = 0; i < mat.length; i++) {
			long r = 0L;
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
