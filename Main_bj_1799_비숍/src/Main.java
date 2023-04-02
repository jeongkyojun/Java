import java.util.*;
import java.io.*;

public class Main {
	static int res;
//	static boolean[][] tmp;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		boolean[][] mat = new boolean[n][n];
//		tmp = new boolean[mat.length][mat.length];
		res = 0;
		int max =0;
		for(int i=0;i<n;i++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j=0;j<n;j++)
			{
				mat[i][j] = st.nextToken().equals("0");
				if(!mat[i][j]) max++;
			}
		}
		tracking(mat,0,n,0,max,0);
		System.out.println(res);
	}

	static void tracking(boolean[][] mat, int row,int n, int p,int max,int val) {
		if(max+val<res)
			return;
		if(val+mat.length<res)
			return;
		if(n==p) {
			if(row==mat.length-1)
			{
				// 여기서 확인한다.
				if(res<val)
					res = val;
			}
			else
				tracking(mat,row+1,mat.length,0,max,val);
			return;
		}
		int sub = 0;
		boolean[][] tmp = new boolean[mat.length][mat.length];
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length;j++)
				tmp[i][j] = mat[i][j];
		}
		if(!mat[row][p]) {
			// row , p 를 기점으로 
			//		row-i, p-i 와 row+i, p+i
			// 			row+i = 0일 때부터 row, row+i<mat.length까지
			//		row+i, p-i 와 row-i, p+i를 수행
			for(int i=-row;i<mat.length-row;i++)
			{
				if(p+i>=0 && p+i<mat.length) {
					if(!tmp[row+i][p+i]) sub++;
					tmp[row+i][p+i] = true;
				}
				if(p-i>=0 && p-i<mat.length) {
					if(!tmp[row+i][p-i]) sub++;
					tmp[row+i][p-i] = true;
				}
			}
			mat[row][p] = true;
			sub = 1;
			tracking(tmp,row,n,p+1,max-sub,val+1);
		}
		tracking(mat,row,n,p+1,max-sub,val);
	}
}
