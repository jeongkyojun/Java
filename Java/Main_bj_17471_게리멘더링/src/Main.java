/*
 * Main_bj_17471_게리맨더링(G4)
 */
import java.util.*;
import java.io.*;


public class Main {

	static int min = 0;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17471.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			
			int N = Integer.parseInt(bf.readLine());
			boolean[][] mat = new boolean[N][N];
			int[] ppl = new int[N];
			int[] U = new int[N];
			boolean[] team = new boolean[N];
			int max=0;
			min = -1;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++) {
				ppl[i] = Integer.parseInt(st.nextToken());
				max+=ppl[i];
			}
			for(int i=0;i<N;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int cnt = Integer.parseInt(st.nextToken());
				for(int j=0;j<cnt;j++)
				{
					int t = Integer.parseInt(st.nextToken());
					mat[i][t-1] = true;
					mat[t-1][i] = true;
				}
			}
			Divide(mat,team,ppl,0,max,0,0);
			System.out.println(min);
		}
	}
	static void Divide(boolean[][] mat,boolean[] team, int[] ppl, int cnt, int Ateam, int Bteam,int num)
	{
		if(cnt==team.length)
		{
			// 하나로 통일되어있는경우 return
			if(num==0||num==team.length)return;
			int sub = Math.abs(Ateam-Bteam);
			if(min!=-1 && sub>min) return;
			
			if(!check(mat, team)) return;
			
			
			if(min==-1||min>sub)
			{
				min = sub;
			}
			return;
		}
		// 팀별로 나눈다.
		team[cnt]=true;
		Divide(mat, team, ppl, cnt+1, Ateam,Bteam,num+1);
		team[cnt]=false;
		Divide(mat, team, ppl, cnt+1, Ateam-ppl[cnt],Bteam+ppl[cnt],num);
	}

	static boolean check(boolean[][] mat, boolean[] team) {
		boolean[] chk = new boolean[team.length];
		Queue<Integer> q = new LinkedList<Integer>();
		int tnum = 0;
		for(int i=0;i<team.length;i++)
		{
			q.clear();
			if(chk[i]) continue;
			
			boolean myteam = team[i];
			chk[i] = true;
			q.offer(i);
			if(++tnum>2) return false;
			
			while(!q.isEmpty())
			{
				int tmp = q.poll();
				for(int a=0;a<mat.length;a++)
				{
					// 1. 연결되어있고 2. 방문하지 않았으며 3. 내 팀인경우
					if(mat[tmp][a] && !chk[a] && myteam == team[a])
					{
						chk[a] = true;
						q.offer(a);
					}
				}
			}
		}
		return true;
	}
}
