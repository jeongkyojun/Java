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
					{
						mat[i][j] = '.';
						red = new int[] { i, j };
					}
					if (mat[i][j] == 'B')
					{
						mat[i][j] = '.';
						blue = new int[] { i, j };
					}
				}
			}
			chk[red[0]][red[1]][blue[0]][blue[1]] = true;

			Queue<int[]> rq = new LinkedList<int[]>();
			rq.offer(new int[] {red[0],red[1],blue[0],blue[1]});
			int time = 0;
			// bfs 수행
			while (!rq.isEmpty()) {
				++time;
				if(time>10) break;
				int size = rq.size();
				for (int x = 0; x < size; x++) {
					int[] b = rq.poll();
					for (int d = 0; d < 4; d++) {
						int[] tmp = new int[]{b[0],b[1],b[2],b[3]};
						boolean red_move = true;
						boolean blue_move = true;
						boolean red_goal_in = false;
						boolean blue_goal_in = false;
						while(red_move || blue_move) {
							// 칸이 존재하는 경우, 이동기회가 있는경우
							if(0<=tmp[0]+di[d] && tmp[0]+di[d]<N 
									&& 0<=tmp[1]+dj[d] && tmp[1]+dj[d]<M
									&&red_move) {
								// 만약 빈공간에 다른 구슬이 없으면 이동
								if(mat[tmp[0]+di[d]][tmp[1]+dj[d]] == '.' 
										&& (tmp[0]+di[d]!=tmp[2] || tmp[1]+dj[d]!=tmp[3]))
								{
									tmp[0]+=di[d];
									tmp[1]+=dj[d];
								}
								else if(mat[tmp[0]+di[d]][tmp[1]+dj[d]] == 'O') { // 홀에 들어갈 경우
									tmp[0]+=di[d]; // 이동 처리
									tmp[1]+=dj[d]; 
									red_goal_in = true;
									red_move = false; // 이후 이동권 박탈
								}
								//다른 구슬이 존재하는경우
								else if(tmp[0]+di[d]==tmp[2] && tmp[1]+dj[d]==tmp[3]) {
									if(!blue_move) // 그 구슬이 움직이지 못할경우
										red_move = false; // 이동권 박탈
								}
								else {
									red_move = false;
								}
							}
							if(0<=tmp[2]+di[d] && tmp[2]+di[d]<N
									&& 0<=tmp[3]+dj[d] && tmp[3]+dj[d]<M
									&&blue_move) {
								if(mat[tmp[2]+di[d]][tmp[3]+dj[d]] == '.' 
										&& (tmp[2]+di[d]!=tmp[0] || tmp[3]+dj[d]!=tmp[1]))
								{
//									System.out.print("4");
									tmp[2]+=di[d];
									tmp[3]+=dj[d];
								}
								else if(mat[tmp[2]+di[d]][tmp[3]+dj[d]] == 'O') {
//									System.out.print("5");
									tmp[2]+=di[d];
									tmp[3]+=dj[d];
									blue_goal_in = true;
									blue_move = false;
								}
								//다른 구슬이 존재하는경우
								else if(tmp[2]+di[d]==tmp[0] && tmp[3]+dj[d]==tmp[1]) {
									if(!red_move) // 그 구슬이 움직이지 못할경우
										blue_move = false; // 이동권 박탈
								}
								else {
//									System.out.print("6");
									blue_move = false;
								}
							}
//							System.out.println(Arrays.toString(tmp)+red_move+" , "+blue_move);
						}
						if(red_goal_in) {
							if(!blue_goal_in)
							{
								System.out.println(time);
								continue tc;
							}
						}
						else if(!red_goal_in && !blue_goal_in && !chk[tmp[0]][tmp[1]][tmp[2]][tmp[3]]) {
							chk[tmp[0]][tmp[1]][tmp[2]][tmp[3]] = true;
							rq.offer(tmp);
						}
					}
				}
			}
			System.out.println(-1);
		}
	}

}
