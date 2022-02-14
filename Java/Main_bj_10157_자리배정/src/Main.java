import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_bj_10157.txt")));
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1;test_case <=T;test_case++)
		{
			
			int[] dr = new int[] {0,1,0,-1};//상(0,1), 우(1,0), 하(0,-1), 좌(-1,0)
			int[] dc = new int[] {1,0,-1,0};
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int[][] mat = new int[r][c];
			int num = Integer.parseInt(bf.readLine());
			int a = 0,b=0,cnt=0;
			mat[0][0] = 1;
			for(int i=2;i<=num;i++)
			{
				if(0<=a+dr[cnt%4]&&a+dr[cnt%4]<r&&0<=b+dc[cnt%4]&&b+dc[cnt%4]<c&&mat[a+dr[cnt%4]][b+dc[cnt%4]]==0)
				{
					a+=dr[cnt%4];
					b+=dc[cnt%4];
					mat[a][b] = i;
				}
				// 여기에 강제 종료 조건을 추가해야 한다.
				else
				{
					cnt++;
				}
			}
			System.out.println((a+1)+" "+(b+1));
			System.out.println();
		}
		
	}

}
