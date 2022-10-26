import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 0, 1, 0 };
	static int[] dj = new int[] { 0, -1, 0, 1 };
	static int E;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19238.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#"+test_case+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			Queue<int[]> q = new LinkedList<int[]>();
			int[][] mat = new int[N][N];
			int[][] point = new int[M][4];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {

					mat[i][j] = Integer.parseInt(st.nextToken());
					if (mat[i][j] == 1)
						mat[i][j] = -1;
				}
			}
			/*
			System.out.println();
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(mat[i]));
			}
			System.out.println();
			*/
			st = new StringTokenizer(bf.readLine());
			int pi = Integer.parseInt(st.nextToken()) - 1;
			int pj = Integer.parseInt(st.nextToken()) - 1;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int is = Integer.parseInt(st.nextToken());
				int js = Integer.parseInt(st.nextToken());
				int ie = Integer.parseInt(st.nextToken());
				int je = Integer.parseInt(st.nextToken());
				point[i] = new int[] { is - 1, js - 1, ie - 1, je - 1 };
				mat[is - 1][js - 1] = i + 1;
			}
			
			System.out.println();
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(mat[i]));
			}
			System.out.println();
			
			Taxi(q, mat, point, pi, pj, M);
			System.out.println(E);
		}
	}

	static void Taxi(Queue<int[]> q, int[][] mat, int[][] point, int pi, int pj, int M) {
		int myi = pi, myj = pj;
		for (int i = 0; i < M; i++) { // 손님 수 만큼 반복문 수행
			int t = search(q, mat, point, myi, myj); // 손님의 번호 가져오기
			if (t == -1) {
				E = -1; // 손님을 못찾았을 경우 종료
				return;
			}
			if (move(q, mat, point[t][0], point[t][1], point[t][2], point[t][3])) {
				myi = point[t][2];
				myj = point[t][3];
			}
			else
			{
				E = -1; // move에서 false 반환 = 이동 실패를 의미
				return;
			}
		}
	}

	static boolean move(Queue<int[]> q, int[][] mat, int pi, int pj, int ei, int ej) {
		q.clear();
		q.offer(new int[] { pi, pj });
		boolean[][] chk = new boolean[mat.length][mat.length];
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			time++;
			if (time > E)
				break;
			for (int t = 0; t < size; t++) {
				int[] tmp = q.poll();
				// System.out.println("time : "+time);
				// System.out.println("tmp : "+Arrays.toString(tmp));
				for (int d = 0; d < 4; d++) {

					// 1. 칸이 존재하고
					// 2. mat[][] == -1 이 아니며(벽이 아니며)
					// 3. chk[][] = false이다(방문한적 없다)
					if (0 <= tmp[0] + di[d] && tmp[0] + di[d] < mat.length && 0 <= tmp[1] + dj[d]
							&& tmp[1] + dj[d] < mat.length && mat[tmp[0] + di[d]][tmp[1] + dj[d]] != -1
							&& !chk[tmp[0] + di[d]][tmp[1] + dj[d]]) {
						// 종료조건 : 손님이 있는경우
						if (tmp[0] + di[d] == ei && tmp[1] + dj[d] == ej) {
							// 시간이 에너지보다 클경우
							if (E < time)
								return false; // 실패
							E += time;
							return true;
						}
						// 그 외 -> true로 표시하고 큐에 넣는다.
						// System.out.println("input : "+(tmp[0] + di[d])+" , "+(tmp[1] + dj[d]));
						chk[tmp[0] + di[d]][tmp[1] + dj[d]] = true;
						q.offer(new int[] { tmp[0] + di[d], tmp[1] + dj[d] });
					}
				}
			}
		}
		return false;
	}

	static int search(Queue<int[]> q, int[][] mat, int[][] point, int pi, int pj) {
		q.clear(); // 큐 초기화
		q.offer(new int[] { pi, pj }); // 현재 위치 큐에 담기
		boolean[][] chk = new boolean[mat.length][mat.length]; // 방문여부 확인
		int time = 0;
		
		int[][] result = new int[point.length][2];
		int cnt = 0; // 카운트
		
		if (mat[pi][pj] != 0) {
			result[cnt++] = new int[] {pi,pj};
		}
		
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			if(cnt!=0) // 카운트가 0이 아닌경우 : 손님을 탐색했다
			{
				// 최솟값 확인
				int min[] = new int[] {Integer.MAX_VALUE,Integer.MAX_VALUE};
				for(int i=0;i<cnt;i++)
				{
					// 손님들을 확인 -> 행을 우선적으로 비교
					if(result[i][0]<min[0])
					{
						min = result[i];
					}
					else if(result[i][0]==min[0]) //행이 같은경우
					{
						if(result[i][1]<min[1])// 열을 비교
							min = result[i];
					}
				}
				int res = mat[min[0]][min[1]]; // 결과 번호를 가져온다.
				mat[min[0]][min[1]] = 0; // 해당 손님은 택시를 탓으므로 맵 위에 없다.
				E -= time; // 연료를 걸린 시간만큼 차감
				//System.out.println("search "+res);
				//System.out.println("time : "+time);
				return res-1; // 손님 번호 반환
			}			
			int size = q.size(); // 큐의 사이즈를 담는다
			time++; // 시간값 증가
			if (time > E) // 만약 시간이 에너지보다 크다 = 이동 실패, 반복문을 나온다.
				break;
			// 시간만큼 반복한다.
			for (int t = 0; t < size; t++) {
				int[] tmp = q.poll(); //큐에서 값을 꺼낸다.
				// 사방탐색
				for (int d = 0; d < 4; d++) { 
					
					// 칸이 존재하고, 방문된적 없을때, 벽이 아닐때
					if (0 <= tmp[0] + di[d] && tmp[0] + di[d] < mat.length && 0 <= tmp[1] + dj[d]
							&& tmp[1] + dj[d] < mat.length && mat[tmp[0] + di[d]][tmp[1] + dj[d]] != -1
							&& !chk[tmp[0] + di[d]][tmp[1] + dj[d]]) {
						
						// 손님이 있는경우 result에 손님의 위치를 저장한다.
						if (mat[tmp[0] + di[d]][tmp[1] + dj[d]] != 0) {
							result[cnt++] = new int[] {tmp[0] + di[d],tmp[1] + dj[d]};
						}
						// 방문한 곳을 true로 체크한다.
						chk[tmp[0] + di[d]][tmp[1] + dj[d]] = true;
						// 큐에 값을 넣는다.
						q.offer(new int[] { tmp[0] + di[d], tmp[1] + dj[d] });
					}
				}
			}
			
		}
		
		return -1; // 반복문을 나오면 -1반환(실패)
	}
}
