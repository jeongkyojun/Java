import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= t; tc++) {
			System.out.println("#" + tc + " ");

			// 1000 x 1000 = 1000000 = 최댓값
			int n = Integer.parseInt(bf.readLine());
			int[][][] mat = new int[n][3][3]; // [개수][현재 집의 색][맨 처음 집의 색]
			int min = 1000001;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j=0;j<3;j++)
			{
				mat[0][0][j] = Integer.parseInt(st.nextToken());
				mat[0][1][j] = mat[0][0][j];
				mat[0][2][j] = mat[0][0][j];
			}
			System.out.print(Arrays.toString(mat[0][0])+" ");
			System.out.print(Arrays.toString(mat[0][1])+" ");
			System.out.println(Arrays.toString(mat[0][2]));
			for (int i = 1; i < n-1; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 3; j++) { // j : 빨강, 파랑, 초록을 의미
					int house = Integer.parseInt(st.nextToken());
					if(i==1) {
						if(j!=0)
							mat[i][j][0] = house+RGB_min(mat[i-1][(j+1)%3][0],mat[i-1][(j+2)%3][0]); 
						if(j!=1)
							mat[i][j][1] = house+RGB_min(mat[i-1][(j+1)%3][1],mat[i-1][(j+2)%3][1]);
						if(j!=2)
							mat[i][j][2] = house+RGB_min(mat[i-1][(j+1)%3][2],mat[i-1][(j+2)%3][2]);
					}
					// 맨 처음이 0이고 현재 색이 j인 집 = 맨 처음이 0이고 이전색이 j-1과 j-2인 집과 색을 비교
					else {
						mat[i][j][0] = house+RGB_min(mat[i-1][(j+1)%3][0],mat[i-1][(j+2)%3][0]); 
						mat[i][j][1] = house+RGB_min(mat[i-1][(j+1)%3][1],mat[i-1][(j+2)%3][1]);
						mat[i][j][2] = house+RGB_min(mat[i-1][(j+1)%3][2],mat[i-1][(j+2)%3][2]);
					}
				}
				System.out.print(Arrays.toString(mat[i][0])+" ");
				System.out.print(Arrays.toString(mat[i][1])+" ");
				System.out.println(Arrays.toString(mat[i][2]));
			}
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<3;j++) {
				int house = Integer.parseInt(st.nextToken());
				if(j!=0)
					mat[n-1][j][0] = house+RGB_min(mat[n-2][(j+1)%3][0],mat[n-2][(j+2)%3][0]);
				if(j!=1)
					mat[n-1][j][1] = house+RGB_min(mat[n-2][(j+1)%3][1],mat[n-2][(j+2)%3][1]);
				if(j!=2)
					mat[n-1][j][2] = house+RGB_min(mat[n-2][(j+1)%3][2],mat[n-2][(j+2)%3][2]);
				
				for(int k=0;k<3;k++) {
					if(mat[n-1][j][k]==0) continue;
					if(min>mat[n-1][j][k])
						min = mat[n-1][j][k];
				}
			}
			System.out.println(min);
		}

	}
	
	static int RGB_min(int a, int b) {
		if(a==0&&b==0) return -1;
		if(a==0) return b;
		if(b==0) return a;
		if(a<b) return a;
		return b;
	}
}
