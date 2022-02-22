package main.bj_21608;

import java.util.*;
import java.io.*;

보류문제 풀어봅시다!

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_21608.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) ////
		{ //////////////////////////////////////////////////

			int N = Integer.parseInt(bf.readLine());
			int NN = (int) Math.pow(N, 2);
			int[][][] mat = new int[N][N][4]; // 좌표 (a,b)에 인접한 행렬
			int[][] point = new int[NN][2];

			for (int i = 0; i < NN; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int r = Integer.parseInt(st.nextToken());
			}
		} // ///////////////////////////////////////////////

	}

}
