import java.util.*;
import java.io.*;

public class Solution {

	static int min;
	static int[] di = new int[] {-1,1,0,0}; //상, 하, 좌, 우
	static int[] dj = new int[] {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_sw_1767.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++)
		{
			System.out.println("#"+test_case+" ");
			int N = Integer.parseInt(bf.readLine());
			
			int[][] mat = new int[N][N];
			int[][] processor = new int[145][];
			int pnum=0;
			for(int i=0;i<N;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<N;j++)
				{
					mat[i][j] = Integer.parseInt(st.nextToken());
					if(mat[i][j]==1)
					{
						processor[pnum++] = new int[] {i,j};
					}
				}
			}
			min = Integer.MAX_VALUE;
			process_perm(mat, processor, pnum, 0, 0,-1);
			System.out.println(min);
		}
		
	}
	
	static void process_perm(int[][] mat, int[][] processor, int n, int cnt,int res,int dir)
	{
		System.out.println("====="+cnt+"=====");
		
		if(cnt==n)
		{
			for(int i=0;i<mat.length;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}
			System.out.println();
			if(res<min)
				min = res;
			return;
		}
		// 프로세서 위치 확인
		int pi = processor[cnt][0];
		int pj = processor[cnt][1];
		
		// 맨 끝자리 프로세서인 경우 pass
		if(pi==0||pi==mat.length-1||pj==0||pj==mat[0].length-1)
		{
			System.out.println(pi+" , "+pj+" size : 0");
			process_perm(mat, processor, n, cnt+1, res,-1);
			return;
		}
		
		int[][] tmp = new int[mat.length][mat[0].length];
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[i].length;j++)
			{
				tmp[i][j] = mat[i][j];
			}
		}
		
		// 선을 긋는다.
		while(dir!=-1 && 0<=pi+di[dir]&& pi+di[dir]<mat.length 
				&& 0<= pj+dj[dir] && pj+dj[dir]<mat[0].length)
		{
			res++;
			tmp[pi+di[dir]][pj+dj[dir]] = 1;
			
			pi+=di[dir];
			pj+=dj[dir];
		}
		
		for(int i=0;i<mat.length;i++)
		{
			System.out.println(Arrays.toString(mat[i]));
		}
		System.out.println();
		
		boolean find;
		for(int i=0;i<4;i++)
		{
			find = false;
			// 직선 확인
			while(pi+di[i]>=0 && pi+di[i]<tmp.length
					&& pj+dj[i]>=0 && pj+dj[i]<tmp[0].length &&
					tmp[pi+di[i]][pj+dj[i]]==0)
			{
				pj+=dj[i];
				pi+=di[i];
				if(pi+di[i]<0||pi+di[i]>=tmp.length
						||pj+dj[i]<0||pj+dj[i]>=tmp[0].length)
				{
					find = true;
					break;
				}
			}
			if(find)
			{
				process_perm(tmp, processor, n, cnt+1, res,i);
			}
		}
	}
}
