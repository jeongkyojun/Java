import java.io.*;
import java.util.*;

public class Main {

	static int[] di = new int[] { -1, 0, 1, 0 };// 상 우 하 좌
	static int[] dj = new int[] { 0, 1, 0, -1 };
	static int min;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17142.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int v_num = 0;
			int[][] v = new int[10][2];
			int[][] mat = new int[N][N];
			Queue<int[]> q = new LinkedList<int[]>();
			int num = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					switch (n) {
					case 2:
						v[v_num][0] = i;
						v[v_num++][1] = j;
						mat[i][j] = -3;
						break;
					case 0:
						mat[i][j] = -1;
						num++;
						break;
					case 1:
						mat[i][j] = -2;
						break;
					}
				}
			}
			min = -1;

			comb(mat,q, v, 0, M, 0, 0, num, v_num);
			System.out.println(min);
		}
	}

	// 메모리 초과로 인해 수정한 사항
	// 1. Queue를 함수에서 생성하는것이 아닌, 외부에서 생성하고 q.clear로 매번 초기화 하는 방식을 사용
	// 2. int[][] tmp 가 아닌 boolean[][] tmp를 사용하여 tmp 생성시 사용되는 메모리 양을 감소시킴
	static void comb(int[][] mat,Queue<int[]>q, int[][] v, int b, int n, int p, int cnt, int num, int size) {
		if (cnt == n) {
			q.clear(); // 큐 초기화
			//int[][] tmp = new int[mat.length][mat[0].length];
			boolean[][]tmp = new boolean[mat.length][mat[0].length];
			
			// bfs를 수행할 칸 tmp를 저장
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[i].length; j++) {
					//tmp[i][j] = mat[i][j];
					if(mat[i][j]==-2)
						tmp[i][j] = true; //벽이면 방문처리
					else
						tmp[i][j] = false;
				}
			}
			
			// 큐에 바이러스 지점을 저장
			for (int i = 0; i < size; i++) {
				int x = 1 << i;
				if ((x & b) != 0) {
					q.offer(new int[] { v[i][0], v[i][1] });
					tmp[v[i][0]][v[i][1]] = true; // 방문처리
				}
			}
			int m = vir_turn(mat, tmp, q, num); // bfs 수행
			if (min == -1 || (m != -1 && min > m))
				min = m;
		}
		for (int i = p; i < size; i++) {
			comb(mat,q, v, (b | (1 << i)), n, i + 1, cnt + 1, num, size);
		}
	}

	static int vir_turn(int[][] mat,boolean[][] tmp, Queue<int[]> q, int num) {
		int max = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			if (num == 0)
				return max;
			max++; // 시간 증가
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				for (int d = 0; d < 4; d++) {
					// 칸이 존재하고, 벽이 아닌경우
					if (0 <= p[0] + di[d] && p[0] + di[d] < mat.length && 0 <= p[1] + dj[d] && p[1] + dj[d] < mat.length
							&& !tmp[p[0] + di[d]][p[1] + dj[d]]) {
						if (mat[p[0] + di[d]][p[1] + dj[d]] == -1) {
							num--;	
						}
						tmp[p[0] + di[d]][p[1] + dj[d]] = true;
						q.offer(new int[] { p[0] + di[d], p[1] + dj[d] });
					}
				}
			}
			
			for (int i = 0; i < mat.length; i++)
				System.out.println(Arrays.toString(mat[i]));
			System.out.println();
		}
		return -1;
	}
}
