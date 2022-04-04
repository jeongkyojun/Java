
import java.io.*;
import java.util.*;

public class Main {

	static int max;
	static int[] di = new int[] {0,-1,0};
	static int[] dj = new int[] {-1,0,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17135.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case =1; test_case<=T;test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			int[] pos = new int[3];
			int[] shoot = new int[N];
			int[][] mat = new int[N][M];
			for(int i=0;i<N;i++)
			{
				st = new StringTokenizer(bf.readLine());
				for(int j=0;j<M;j++)
				{
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = -1;
			Defense(mat, pos, 0, 3,0);
			System.out.println(max);
		}
	}

	static void Defense(int[][] mat, int[] pos, int p, int n,int cnt)
	{
		if(cnt==n)
		{
			//System.out.println(Arrays.toString(pos));
			boolean[][] tmp = new boolean[mat.length][mat[0].length];
			for(int i=0;i<mat.length;i++)
			{
				for(int j=0;j<mat[0].length;j++)
				{
					tmp[i][j] = mat[i][j]==1?true:false;
				}
			}
			int num = Hit(tmp,pos);
			
			if(max<num)
				max = num;
			return;
		}
		for(int i=p;i<mat[0].length;i++)
		{
			pos[cnt] = i;
			Defense(mat,pos,i+1,n,cnt+1);
		}
	}
	
	static int Hit(boolean[][] mat, int[] pos)
	{
		Queue<int[]> q = new LinkedList<int[]>();
		int cnt = 0;
		for(int i=mat.length-1;i>=0;i--)
		{
			for(int j=0;j<3;j++)
			{
				/*
				 * 종료조건을 달아야 함(최대 사정거리)
				 */
				
				//mat : i, pos = j
				// bfs 수행
				q.clear();
				q.offer(new int[] {i,j});
				boolean[][] chk = new boolean[mat.length][mat[0].length]; // 체크용
				bfs:while(!q.isEmpty())
				{
					int[] tmp = q.poll();
					for(int d=0;d<3;d++)
					{
						// 길이 존재하고, 방이 있는경우
						if(0<=tmp[0]+di[d] && tmp[0]+di[d]<mat.length && 0<= tmp[1]+dj[d] && tmp[1]+dj[d]<mat[0].length &&
								!chk[tmp[0]+di[d]][tmp[1]+dj[d]])
						{
							// 적이 있는경우
							if(mat[tmp[0]+di[d]][tmp[1]+dj[d]])
							{
								// 적을 사살
								mat[tmp[0]+di[d]][tmp[1]+dj[d]] = false;
								cnt++;// 카운트 증가
								break bfs;// 탐색 끝
							}
							chk[tmp[0]+di[d]][tmp[1]+dj[d]] = true; // 못찾으면 탐색처리
							q.offer(new int[] {tmp[0]+di[d],tmp[1]+dj[d]}); // 다음 번에 하도록
						}
					}
				}
			}
		}
		return cnt;
	}

}
