package main.bj_10819;

import java.util.*;
import java.io.*;

public class Main {

	static int res;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_10819.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			res = -1;
			int N = Integer.parseInt(bf.readLine());
			int[] mat = new int[N];
			int[] r = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine()," ");
			for(int i=0;i<N;i++)
			{
				mat[i] = Integer.parseInt(st.nextToken());
			}
			
			perm(mat,r,0,N,0);
			System.out.println(res);
		}
	}
	static void perm(int[] mat,int[] R, int flag, int n, int cnt)
	{
		if(n==cnt)
		{
			int sum = 0;
			for(int i=0;i<mat.length-1;i++)
			{
				sum+=Math.abs(R[i]-R[i+1]);
			}
			if(sum>res)
				res = sum;
			return;
		}
		for(int i=0;i<mat.length;i++)
		{
			if((flag&(1<<i))==0)
			{
				R[cnt] = mat[i];
				perm(mat,R, flag|(1<<i),n,cnt+1);
			}
		}
	}
}
