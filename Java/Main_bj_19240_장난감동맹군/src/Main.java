import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19240.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			List<List<Integer>> lists = new ArrayList<List<Integer>>();
			int[] U = new int[N];
			boolean Able = true;
			for(int i=0;i<N;i++)
			{
				lists.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				lists.get(a).add(b);
				lists.get(b).add(a);
			}
			Able = check(lists, U);
			if (Able)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

	static boolean check(List<List<Integer>> mat, int[] team) {
		boolean[] chk = new boolean[mat.size()];
		for(int i=0;i<mat.size();i++)
		{
			if(chk[i]) continue;	// 이미 방문한 node는 지나친다.
			//System.out.println(i);
			if(dfs(mat,team,chk,i,1)) // true 가 반환되면 불가능한 경우의 수가 된다.
			{
				return false;
			}
		}
		return true;
	}
	static boolean dfs(List<List<Integer>> mat, int[] team, boolean[] chk,int node,int nums) {
		team[node] = nums; // 방문처리와 팀 처리 수행
		chk[node] = true;
		for(int i=0;i<mat.get(node).size();i++)
		{
			
			if(nums!=0 && team[mat.get(node).get(i)] ==nums)
				return true;
			
			if(chk[mat.get(node).get(i)]) continue; // 방문한 노드는 지나친다.
			
			else
			{
				if(nums==1)
					if(dfs(mat,team,chk,mat.get(node).get(i),2))
						return true;
				if(nums==2)
					if(dfs(mat,team,chk,mat.get(node).get(i),1))
						return true;
			}
		}
		return false;
	}
}
