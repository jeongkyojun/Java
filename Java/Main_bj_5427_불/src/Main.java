import java.util.*;
import java.io.*;

public class Main {

	static int[] di = new int[] {-1,0,1,0};
	static int[] dj = new int[] {0,-1,0,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_5427.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		tc: for(int test_case=1;test_case<=T;test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] mat = new int[N][M];
			Queue<int[]> q = new LinkedList<int[]>();
			Queue<int[]> me = new LinkedList<int[]>();
			boolean next = false;
			for(int i=0;i<N;i++)
			{
				String str = bf.readLine();
				for(int j=0;j<M;j++)
				{
					//System.out.print(str.charAt(j));
					switch(str.charAt(j))
					{
					case '#':
						mat[i][j] = 1;
						break;
					case '*':
						q.offer(new int[] {i,j});
						mat[i][j] = 2;
						break;
					case '@':
						me.offer(new int[] {i,j});
						if(i==0||j==0||i==N-1||j==M-1)
						{
							next = true;
						}
						mat[i][j] = 3;
						break;
					}
				}
				//System.out.println();
			}
			if(next)
			{
				System.out.println("1");
				continue tc;
			}
			/*
			for(int i=0;i<mat.length;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}
			*/
			int time = 1;
			while(!me.isEmpty())
			{
				time++;
				int size = q.size();
				for(int x=0;x<size;x++)
				{
					int[] tmp = q.poll();
					for(int d=0;d<4;d++)
					{
						if(0<=tmp[0]+di[d] && tmp[0]+di[d]<N&& 0<=tmp[1]+dj[d] && tmp[1]+dj[d]<M 
								&& mat[tmp[0]+di[d]][tmp[1]+dj[d]]!=1 && mat[tmp[0]+di[d]][tmp[1]+dj[d]]!=2)
						{
							mat[tmp[0]+di[d]][tmp[1]+dj[d]] = 2;
							q.offer(new int[] {tmp[0]+di[d], tmp[1]+dj[d]});
						}
					}
				}
				size = me.size();
				for(int x=0;x<size;x++)
				{
					int[] tmp = me.poll();
					for(int d=0;d<4;d++)
					{
						if(0<=tmp[0]+di[d] && tmp[0]+di[d]<N&& 0<=tmp[1]+dj[d] && tmp[1]+dj[d]<M 
								&& mat[tmp[0]+di[d]][tmp[1]+dj[d]]==0)
						{
							if(tmp[0]+di[d]==0||tmp[1]+dj[d]==0||tmp[0]+di[d]==N-1 || tmp[1]+dj[d]==M-1)
							{	
								System.out.println(time);
								continue tc;
							}
							mat[tmp[0]+di[d]][tmp[1]+dj[d]] = 3;
							me.offer(new int[] {tmp[0]+di[d], tmp[1]+dj[d]});
						}
					}
				}
			}
			System.out.println("IMPOSSIBLE");
		}

	}

}
