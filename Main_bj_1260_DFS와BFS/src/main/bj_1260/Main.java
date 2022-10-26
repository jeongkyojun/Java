package main.bj_1260;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1260.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case<=T;test_case++)
		{
			System.out.println("#"+test_case);
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			boolean[][] mat = new boolean[N+1][N+1];
			boolean[] B = new boolean[N+1];
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int str = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				mat[str][end] = true;
				mat[end][str] = true;
			}
			DFS(mat,B,V);
			System.out.println();
			for(int i=0;i<N+1;i++)
			{
				B[i] = false;
			}
			BFS(mat,B,V);
			System.out.println();
		}
	}
	static void DFS(boolean[][] mat, boolean[] B, int V)
	{
		B[V] = true;
		System.out.print(V+" ");
		for(int i=1;i<mat[0].length;i++)
		{
			if(mat[V][i])
			{
				if(!B[i])
					DFS(mat,B,i);
			}
		}
	}
	static void BFS(boolean[][] mat, boolean[] B, int V)
	{
		B[V] = true;
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(V);
		while(!Q.isEmpty())
		{
			int a = Q.poll();
			System.out.print(a+" ");
			for(int i=1;i<mat[a].length;i++)
			{
				if(mat[a][i])
				{
					if(!B[i])
					{
						B[i] = true;
						Q.offer(i);
					}
				}
			}
		}
	}
}
