import java.util.*;
import java.io.*;

풀이중

class node implements Comparable<node>{
	int a,b,w;

	node(int a, int b, int w)
	{
		this.a = a;
		this.b = b;
		this.w = w;
	}
	
	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		return Integer.compare(w, o.w);
	}
	
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1240.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N][N];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken());
				mat[a][b] = w;
				mat[b][a] = w;
				makeLength(mat,a,b,w);
				for(int n=0;n<N;n++)
					System.out.println(Arrays.toString(mat[n]));
				System.out.println();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				System.out.println(mat[a - 1][b - 1]);
			}
		}
	}
	static void makeLength(int[][] mat, int a, int b,int w)
	{
		Queue<node> pq = new LinkedList<node>();
		boolean[][] chk = new boolean[mat.length][mat.length];
		pq.offer(new node(a,b,w));
		while(!pq.isEmpty())
		{
			node tmp = pq.poll();
			if(chk[tmp.a][tmp.b]) continue;
			chk[tmp.a][tmp.b] = true;
			chk[tmp.b][tmp.a] = true;
			System.out.println(tmp.a+" , "+tmp.b);
			for(int i=0;i<mat.length;i++)
			{
				if(i==tmp.a || i==tmp.b) continue;
				if(mat[i][tmp.a]!=0)
				{
					if(mat[i][tmp.b]==0||mat[i][tmp.b]>mat[i][tmp.a]+w)
					{
						mat[i][tmp.b] = mat[i][tmp.a]+w;
						mat[tmp.b][i] = mat[i][tmp.a]+w;
						pq.offer(new node(i,tmp.b,mat[i][tmp.a]+w));
					}
				}
				if(mat[i][tmp.b]!=0)
				{
					if(mat[i][tmp.a]==0||mat[i][tmp.a]>mat[i][tmp.b]+w)
					{
						mat[i][tmp.a] = mat[i][tmp.b]+w;
						mat[tmp.a][i] = mat[i][tmp.b]+w;
						pq.offer(new node(i,tmp.a,mat[i][tmp.b]+w));
					}
				}
			}
		}
	}
}
