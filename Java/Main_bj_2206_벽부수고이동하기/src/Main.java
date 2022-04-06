import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] { -1, 0, 1, 0 };
	static int[] dj = new int[] { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2206.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N][M];
			int[][][] route = new int[N][M][2];
			
			
			route[N - 1][M - 1] = new int[] { -1, -1 };

			for (int i = 0; i < N; i++) {
				String str = bf.readLine();
				for (int j = 0; j < M; j++) {
					mat[i][j] = str.charAt(j) - '0';
				}

			}

			if(N==1 && M==1)
			{
				System.out.println(1);
				continue;
			}
			
			Queue<int[]> q = new LinkedList<int[]>();
			q.offer(new int[] { 0, 0, 0});

			int time = 1;
			rot : while (!q.isEmpty()) {
				time++;
				//System.out.println("time : "+time);
				int size = q.size();
				for (int x = 0; x < size; x++) {
					int[] tmp = q.poll();
					if(tmp[0]==N-1 && tmp[1]==M-1)
						break rot;
					for (int d = 0; d < 4; d++) {
						if(0<=tmp[0]+di[d] && tmp[0]+di[d]< N && 0<=tmp[1]+dj[d] && tmp[1]+dj[d]<M)
						{
							if(mat[tmp[0]+di[d]][tmp[1]+dj[d]]==0) // 벽이 아닌경우
							{
								//System.out.println("--");
								// 만약 방문한적 없는 길이라면 값을 갱신한다.
								if(route[tmp[0]+di[d]][tmp[1]+dj[d]][tmp[2]]==0||route[tmp[0]+di[d]][tmp[1]+dj[d]][tmp[2]]==-1)
								{
									route[tmp[0]+di[d]][tmp[1]+dj[d]][tmp[2]] = time;
									q.offer(new int[] {tmp[0]+di[d],tmp[1]+dj[d],tmp[2]});
								}
							}
							else
							{
								//System.out.println("--?");
								//아직 벽을 뚫은적 없고, 빈공간이라면
								//System.out.println(Arrays.toString(tmp));
								//System.out.println(route[tmp[0]+di[d]][tmp[1]+dj[d]][1]);
								if(tmp[2]==0 && route[tmp[0]+di[d]][tmp[1]+dj[d]][1]==0||route[tmp[0]+di[d]][tmp[1]+dj[d]][1]==-1)
								{
									//System.out.println("!");
									route[tmp[0]+di[d]][tmp[1]+dj[d]][1] = time;
									q.offer(new int[] {tmp[0]+di[d],tmp[1]+dj[d],1});
								}
							}
						}
					}
					/*
					for(int i=0;i<N;i++)
					{
						for(int j=0;j<M;j++)
							System.out.print(route[i][j][1]+" ");
						System.out.println();
					}
					System.out.println();*/
				}
			}
			/*
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
					System.out.print(route[i][j][0]+" ");
				System.out.println();
			}
			System.out.println();
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
					System.out.print(route[i][j][1]+" ");
				System.out.println();
			}
			
			System.out.println("result");
			*/
			if(route[N-1][M-1][0]==-1)
				System.out.println(route[N-1][M-1][1]);
			else if(route[N-1][M-1][1]==-1)
				System.out.println(route[N-1][M-1][0]);
			else
				System.out.println(Integer.min(route[N-1][M-1][1], route[N-1][M-1][0]));
		}

	}

}
