
import java.util.*;
import java.io.*;

public class Main {
	
	static int res;
	static int[] dj = new int[] {0,0,-1,1}; // 상하좌우
	static int[] di = new int[] {-1,1,0,0};
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_1987.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.println("#"+test_case+" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			res = 1;
			
			char[][] mat = new char[R][C];
			int [][] set = new int[R][C];
			boolean[] check = new boolean[27];
			for(int i=0;i<R;i++)
			{
				String str = bf.readLine();
				for(int j=0;j<C;j++)
				{
					mat[i][j] = str.charAt(j);
				}
			}
			check[mat[0][0]-65] = true;
			Tracking(mat,set,check,mat[0][0],1,0,0);
			System.out.println(res);
		}
	}
	
	static void Tracking(char[][] mat,int[][] n, boolean[] check, char before,int move,int x,int y)
	{
		
		for(int i=0;i<4;i++)
		{
			// 다음 이동 발판
			int x_next = x+ dj[i];
			int y_next = y+di[i];
			
			// 길이 존재하는지 확인, 
			//알파벳 밟은적 없는지 확인 
			if(0<=x_next&&x_next<mat[0].length&&0<=y_next&&y_next<mat.length&&
					!check[mat[y_next][x_next]-65])
			{	
				check[mat[y_next][x_next]-65] = true;
				Tracking(mat,n,check,before,move+1,x_next,y_next);
				check[mat[y_next][x_next]-65] = false;
			}
			
		}
		if(res<move)
			res = move;
	}
}
