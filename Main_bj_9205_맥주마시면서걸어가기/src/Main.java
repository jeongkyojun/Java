import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_9205.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		tc:for(int test_case=1;test_case<=T;test_case++)
		{
			int N = Integer.parseInt(bf.readLine());
			int beer = 20;
			int[][] dist = new int[2+N][2];
			
			for(int i=0;i<2+N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<2;j++)
				{
					dist[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> q = new LinkedList<int[]>();
			boolean[] chk = new boolean[N+2];
			// x좌표, y좌표, 방문처리, 이동가능 거리
			q.offer(new int[] {dist[0][0],dist[0][1]});
			chk[0] = true;
			while(!q.isEmpty())
			{
				int[] tmp = q.poll();
				
				for(int i=0;i<dist.length;i++)
				{
					// 이미 방문한 곳은 지나친다.
					if(chk[i]) continue;
					if(Math.abs(tmp[0]-dist[i][0])+Math.abs(tmp[1]-dist[i][1])<=1000)
					{
						if(i==dist.length-1)
						{
							System.out.println("happy");
							continue tc;
						}
						q.offer(new int[] {dist[i][0],dist[i][1]});
						chk[i] = true;
					}
				}
			}
			
			System.out.println("sad");
		}
	}

}
