package main.bj_2162;

CCW 는 좀 공부를 해보자

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2162.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#" + test_case + " ");

			int[] Union = new int[3001];

			int N = Integer.parseInt(bf.readLine());
			int[][] mat = new int[N][4];// 기울기, 나머지, 최소, 최대
			int[] group = new int[N];
			int group_num = 1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 4; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
				Union[i] = i;
				//System.out.println("i : " + i);
				if (i >= 1) {
					boolean find = false;
					for (int j = 0; j < group_num; j++) {
						if (Intersection(mat[i], mat[group[j]])) {
							union(group[j], i, Union);
							find = true;
							break;
						}
					}
					if (!find) {
						group[group_num++] = i; // 안맞는경우 group_num에 기록
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < group_num; j++) {
					if(i==group[j]) continue;
					if (Intersection(mat[i], mat[group[j]])) {
						union(group[j], i, Union);
						break;
					}
				}
			}

			int max_group = -1;
			boolean[] G = new boolean[N+1];
			int[] groups = new int[N+1];
			group_num = 0;
			for (int i = 0; i < N; i++) {
				int g = FindUnion(i, Union);
				//System.out.println(i + " : " + g);
				groups[g]++;
				if (groups[g] > max_group) {
					max_group = groups[g];
				}
				if(!G[g])
				{
					G[g] = true;
					group_num++;
				}
			}
			System.out.println(group_num);
			System.out.println(max_group);
		}
	}

	static boolean union(int a, int b, int[] Union) {
		int AU = FindUnion(a, Union);
		int BU = FindUnion(b, Union);
		if (AU == BU)
			return false;
		else {
			Union[b] = a;
		}
		return true;
	}

	static int FindUnion(int a, int[] Union) {
		if (Union[a] == a)
			return a;
		return Union[a] = FindUnion(Union[a], Union);
	}

	static boolean Intersection(int[] L1, int[] L2) {
		int[] A = new int[] { L1[0], L1[1] };
		int[] B = new int[] { L1[2], L1[3] };
		int[] C = new int[] { L2[0], L2[1] };
		int[] D = new int[] { L2[2], L2[3] };

		// AB,C 와 AB,D의 방향이 다른지 체크
		if (CCW(A[0], A[1], B[0], B[1], C[0], C[1]) * CCW(A[0], A[1], B[0], B[1], D[0], D[1]) < 0
				&& CCW(C[0], C[1], D[0], D[1], A[0], A[1]) * CCW(C[0], C[1], D[0], D[1], B[0], B[1]) > 0)
			return true;
		// 한점이 한 직선에 만나는지를 체크하여 교차여부 확인할것
		if (A[0] == C[0] && A[1] == C[1])
			return true;
		if (A[0] == D[0] && A[1] == D[1])
			return true;
		if (B[0] == C[0] && B[1] == C[1])
			return true;
		if (B[0] == D[0] && B[1] == D[1])
			return true;

		if (CCW(A[0], A[1], B[0], B[1], C[0], C[1]) == 0 && InternalPoint(A[0], A[1], B[0], B[1], C[0], C[1]))
			return true;
		if (CCW(A[0], A[1], B[0], B[1], D[0], D[1]) == 0 && InternalPoint(A[0], A[1], B[0], B[1], D[0], D[1]))
			return true;
		if (CCW(C[0], C[1], D[0], D[1], A[0], A[1]) == 0 && InternalPoint(C[0], C[1], D[0], D[1], A[0], A[1]))
			return true;
		if (CCW(C[0], C[1], D[0], D[1], B[0], B[1]) == 0 && InternalPoint(C[0], C[1], D[0], D[1], B[0], B[1]))
			return true;

		return false;
	}

	static boolean InternalPoint(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {

		int dxAB = Bx - Ax;

		int dyAB = By - Ay;

		int dxAC = Cx - Ax;

		int dyAC = Cy - Ay;

		if ((dxAB * dxAC < 0) || (dyAB * dyAC < 0))
			return false; // 서로 다른 방향이라면 포함하지 않는다

		if (dxAB * dxAB + dyAB * dyAB >= dxAC * dxAC + dyAC * dyAC)
			return true;

		return false;

	}

	static int CCW(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {
		int dxAB = Bx - Ax;
		int dyAB = By - Ay;
		int dxAC = Cx - Ax;
		int dyAC = Cy - Ay;

		// 시계
		if (dxAB * dyAC < dyAB * dxAC)
			return -1;

		// 반시계
		if (dxAB * dyAC > dyAB * dxAC)
			return 1;

		// 일직선
		return 0;
	}
}
