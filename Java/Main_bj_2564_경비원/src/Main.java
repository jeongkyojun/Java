import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2564.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			int[][] mat = new int[5][2];
			for(int i=1;i<=4;i++)
			{
				mat[i][0] = -1; // max값
				mat[i][1] = Integer.MAX_VALUE; // min값
			}
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int X = 2*(R+C); // 전체 둘레
			int n = Integer.parseInt(bf.readLine());
			for(int i=0;i<n;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int dir = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				System.out.println(dir+" , "+p);
				// 최대, 최솟값 정의
				if(mat[dir][0]<p)
					mat[dir][0] = p;
				
				if(mat[dir][1]>p)
					mat[dir][1] = p;
			}
			st = new StringTokenizer(bf.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int res = 0;
			switch(dir)
			{
			/*
			 * 전체 둘레 X,
			 * 현재 내 위치에서 양 끝으로 가장 가까운 두점
			 * 북a -> 서 b (a+b) 		동 b(R-a+b) 		-> b는 가장 작은값
			 * 남a -> 서 b(a+C-b) 	동 b (R-a+C-b)	-> b는 가장 큰 값
			 * 서a -> 남 b (C-a+b) 	북b (a+b)		-> b는 가장 작은값
			 * 동a -> 남 b (C-a+R-b)	북b (a+R-b)		-> b는 가장 큰값
			 */
			case 1: // 북 ->  서(3),동(4)
				res = X - Integer.max(a+mat[3][1], R-a+mat[4][1]);
				break;
			case 2: // 남 -> 서 동
				res = X - Integer.max(a+C-mat[3][0], R-a+C-mat[4][0]);
				break;
			case 3: // 서 ->  남(2), 북(1)
				res = X - Integer.max(C-a+mat[2][1], a+mat[1][1]);
				break;
			case 4: // 동
				res = X - Integer.max(C-a+R-mat[2][0], a+R-mat[1][0]);
				break;
			}
			System.out.println(res);
		}
	}

}
