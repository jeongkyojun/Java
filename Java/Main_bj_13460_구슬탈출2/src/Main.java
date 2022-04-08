import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 0, 1, 0 };
	static int[] dj = new int[] { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_13460.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		tc : for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#"+test_case+" ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			char[][] mat = new char[N][M];

			boolean[][][][] chk = new boolean[N][M][N][M];
			int[] red = new int[2];
			int[] blue = new int[2];
			for (int i = 0; i < N; i++) {
				String str = bf.readLine();
				for (int j = 0; j < M; j++) {
					mat[i][j] = str.charAt(j);
					if (mat[i][j] == 'R')
						red = new int[] { i, j };
					if (mat[i][j] == 'B')
						blue = new int[] { i, j };
				}
			}
			chk[red[0]][red[1]][blue[0]][blue[1]] = true;

			Queue<int[]> rq = new LinkedList<int[]>();
			rq.offer(new int[] {red[0],red[1],blue[0],blue[1]});
			int time = 0;
			// bfs 수행
			while (!rq.isEmpty()) {
				++time;
				int size = rq.size();
				for (int x = 0; x < size; x++) {
					int[] tmp = rq.poll();
					//System.out.println(mat[tmp[0]][tmp[1]]);
					int bi = tmp[2];
					int bj = tmp[3];
					for (int d = 0; d < 4; d++) {
						// 붉은 구슬이 이동가능하고, 벽이 아닌경우
						if (0 <= tmp[0] + di[d] && tmp[0] + di[d] < N && 0 <= tmp[1] + dj[d] && tmp[1] + dj[d] < M
								&& mat[tmp[0] + di[d]][tmp[1] + dj[d]] != '#') {
							// 파란색이 이동할 수 있는경우
							if (0 <= bi + di[d] && bi + di[d] < N && 0 <= bj + dj[d] && bj + dj[d] < M) {
								// 이동시킨다
								//System.out.println("move blue");
								bi += di[d];
								bj += dj[d];
							}

							// 방문한적 없고, 파란 구슬이 원에 들어가면 안되고, 같은 곳에 겹쳐서도 안된다.
							if (!chk[tmp[0] + di[d]][tmp[1] + dj[d]][bi][bj] && mat[bi][bj] != 'O'
									&& (tmp[0] + di[d] != bi || tmp[1] + dj[d] != bj)) {
								// 방문처리
								
								chk[tmp[0] + di[d]][tmp[1] + dj[d]][bi][bj] = true;
								if(mat[tmp[0]+di[d]][tmp[1]+dj[d]]=='O')
								{
									System.out.println(time);
									continue tc;
								}
								// 큐에 넣는다.
								rq.offer(new int[] { tmp[0] + di[d], tmp[1] + dj[d],bi,bj});
							}
						}
					}
				}
			}
			System.out.println(-1);
		}
	}

}
